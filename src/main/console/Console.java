package main.console;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Player;
import main.pieces.Piece;
import main.saveLoad.SaveLoad;
import main.board.Board;
import main.board.Board3D;

import java.io.*;
import java.util.ArrayList;

public class Console extends AnchorPane {

    private Board3D board3D;

    /** Chess boards on the 3D chessboard. */
    private Board[] boards;

    /**
     * Button to save this game.
     */
    private Button saveButton;

    /**
     * Button to loadGame the saved game.
     */
    private Button loadButton;

    private HBox colourButtonsHBox;

    private HBox hBox;

    private ColourTheme colourTheme;

    private Button colourThemeButtons[][];
    /**
     * Player using black pieces.
     */
    private Player blackPlayer;

    /**
     * Player using white pieces.
     */
    private Player whitePlayer;

    private EventController eventController;

    /**
     * Constructor of this Console.
     * 
     * @param boards      Chess boards on the 3D chessboard
     * @param blackPlayer Player using black pieces
     * @param whitePlayer Player using white pieces
     */
    public Console(Board3D board3D, Board[] boards,
                   Player blackPlayer, Player whitePlayer, 
                   ColourTheme colourTheme, EventController eventController) {
        this.board3D = board3D;
        this.boards = boards;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        this.colourTheme = colourTheme;
        this.eventController = eventController;

        colourButtonsHBox = new HBox();
        colourThemeButtons = new Button[3][5];

        hBox = new HBox();
        saveButton = new Button("Save");
        loadButton = new Button("Load");

        getChildren().addAll(colourButtonsHBox, hBox);
    }

    public void initialSetup() {
        addColourButtonsVBox();
        addSaveLoadHBox();
    }

    private void addColourButtonsVBox() {
        for (int i = 0; i < 3; i++) {
            VBox vBox = new VBox();

            for (int j = 0; j < 5; j++) {
                final int verticalLevel = i;
                final int colourThemeNumber = j;
                colourThemeButtons[i][j] = new Button("0" + colourThemeNumber);
                colourThemeButtons[i][j].setOnMouseClicked(event -> changeColourTheme(verticalLevel, colourThemeNumber));
                vBox.getChildren().add(colourThemeButtons[i][j]);
            }

            vBox.setPadding(new Insets(10));
            vBox.setSpacing(12);
            vBox.setStyle("-fx-background-color: #F00");
            colourButtonsHBox.getChildren().add(i, vBox);
        }
        setTopAnchor(colourButtonsHBox, 12.0);
        setRightAnchor(colourButtonsHBox, 10.0);
    }

    private void addSaveLoadHBox() {
        hBox.getChildren().addAll(saveButton, loadButton);

        saveButton.setOnMouseClicked(event -> saveGame());
        loadButton.setOnMouseClicked(event -> loadGame());

        hBox.setPadding(new Insets(0, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #ff6");

        setBottomAnchor(hBox, 0.0);
        setRightAnchor(hBox, 0.0);
    }

    private void changeColourTheme(int verticalLevel, int colourThemeNumber) {
        colourTheme.setColourTheme(colourThemeNumber);

        switch(verticalLevel) {
            case 0:
                colourTheme.paintByTheme(boards[0]);
                break;
            case 1:
                colourTheme.paintByTheme(boards[1]);
                break;
            case 2:
                colourTheme.paintByTheme(boards[2]);
                break;
        }
    }

    /**
     * Saves the current game's turn and pieces.
     */
    private void saveGame() {
        try {
            FileOutputStream f = new FileOutputStream("SaveChessGame.txt");
            ObjectOutput out = new ObjectOutputStream(f);

            SaveLoad saveFile = new SaveLoad();
            saveFile.savePieces(blackPlayer.getPieces(), false);
            saveFile.savePieces(whitePlayer.getPieces(), true);
            saveFile.saveTurn(whitePlayer.isMyTurn());

            out.writeObject(saveFile);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println("FNFE");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("IOE");
            System.out.println(e.getMessage());
        }

        System.out.println("Saved.");
    }

    /**
     * Loads saved game's turn and pieces.
     */
    private void loadGame() {
        try {
            FileInputStream fi = new FileInputStream("SaveChessGame.txt");
            ObjectInput in = new ObjectInputStream(fi);

            SaveLoad loadFile = (SaveLoad) in.readObject();
            in.close();

            boolean whiteTurn = loadFile.loadTurn();

            if (whiteTurn) {
                blackPlayer.finishMyTurn();
                whitePlayer.startMyTurn();
            } else {
                whitePlayer.finishMyTurn();
                blackPlayer.startMyTurn();
            }

            replacePieces(loadFile.loadPieces(false), loadFile.loadPieces(true));
            board3D.removePiecesFromBoards();
            board3D.initialSetup();
            eventController.addColouringListeners();

            colourTheme.paintByTheme(boards);

            eventController.addColouringListeners();
        } catch (FileNotFoundException e) {
            System.out.println("FNFE");
        } catch (IOException e) {
            System.out.println("IOE");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("CNFE");
        }

        System.out.println("Loaded.");
    }

    private void replacePieces(ArrayList<Piece> blackPieces, ArrayList<Piece> whitePieces) {
//        board3D.removePiecesFromBoards();
        blackPlayer.setPieces(blackPieces);
        whitePlayer.setPieces(whitePieces);
    }

}
