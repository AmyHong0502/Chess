package main.console;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Player;
import main.SaveLoad.Save;
import main.board.Board;

import java.io.*;

public class Console extends AnchorPane {

    /**
     * Chess board on the top of this gameboard.
     */
    private Board topBoard;

    /**
     * Chess board on the middle level of this gameboard.
     */
    private Board middleBoard;

    /**
     * Chess board on the bottom of this gameboard.
     */
    private Board bottomBoard;

    /**
     * Button to save this game.
     */
    private Button saveButton;

    /**
     * Button to load the saved game.
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
     * @param topBoard Chess board on the top of this gameboard
     * @param middleBoard Chess board on the middle level of this gameboard
     * @param bottomBoard Chess board on the bottom of this gameboard
     * @param blackPlayer Player using black pieces
     * @param whitePlayer Player using white pieces
     */
    public Console(Board topBoard, Board middleBoard, Board bottomBoard, Player blackPlayer, Player whitePlayer, ColourTheme colourTheme, EventController eventController) {
        this.topBoard = topBoard;
        this.middleBoard = middleBoard;
        this.bottomBoard = bottomBoard;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        this.colourTheme = colourTheme;
        this.eventController = eventController;

        colourButtonsHBox = new HBox();
        colourThemeButtons = new Button[3][5];

        hBox = new HBox();
        saveButton = new Button("Save");
        loadButton = new Button("Load");
        hBox.getChildren().addAll(saveButton, loadButton);

        getChildren().addAll(colourButtonsHBox, hBox);
    }

    public void initialSetup() {
        addVBox();
        addHBox();
    }

    private void addVBox() {
        for (int i = 0; i < 3; i++) {
            VBox vBox = new VBox();

            for (int j = 0; j < 5; j++) {
                final int boardLevel = i;
                final int colourThemeNumber = j;
                colourThemeButtons[i][j] = new Button("0" + colourThemeNumber);
                colourThemeButtons[i][j].setOnMouseClicked(event -> changeColourTheme(boardLevel, colourThemeNumber));
                vBox.getChildren().add(colourThemeButtons[i][j]);
            }

            vBox.setPadding(new Insets(10));
            vBox.setSpacing(12);
            vBox.setStyle("-fx-background-color: #F00");
            colourButtonsHBox.getChildren().add(i, vBox);
        }
        setTopAnchor(colourButtonsHBox, 0.0);
    }

    private void addHBox() {
        saveButton.setOnMouseClicked(event -> saveGame());
        loadButton.setOnMouseClicked(event -> load());

        hBox.setPadding(new Insets(0, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #ff6");

        setBottomAnchor(hBox, 0.0);
    }

    private void changeColourTheme(int zLevel, int colourThemeNumber) {
        colourTheme.setColourTheme(colourThemeNumber);

        switch(zLevel) {
            case 0:
                colourTheme.paintByTheme(topBoard, zLevel);
                break;
            case 1:
                colourTheme.paintByTheme(middleBoard, zLevel);
                break;
            case 2:
                colourTheme.paintByTheme(bottomBoard, zLevel);
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

            Save saveFile = new Save();
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
    private void load() {
        try {
            FileInputStream fi = new FileInputStream("SaveChessGame.txt");
            ObjectInput in = new ObjectInputStream(fi);

            Save saveFile = (Save) in.readObject();
            in.close();

            boolean whiteTurn = saveFile.loadTurn();

            if (whiteTurn) {
                blackPlayer.finishMyTurn();
                whitePlayer.startMyTurn();
            } else {
                whitePlayer.finishMyTurn();
                blackPlayer.startMyTurn();
            }

            topBoard.getChildren().clear();
            middleBoard.getChildren().clear();
            bottomBoard.getChildren().clear();
            blackPlayer.setPieces(saveFile.loadPieces(false));
            whitePlayer.setPieces(saveFile.loadPieces(true));

            topBoard.putTiles();
            middleBoard.putTiles();
            bottomBoard.putTiles();

            colourTheme.paintByTheme(topBoard, 0);
            colourTheme.paintByTheme(middleBoard, 1);
            colourTheme.paintByTheme(bottomBoard, 2);
            eventController.addColouringListener();
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

}
