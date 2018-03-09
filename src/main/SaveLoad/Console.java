package main.SaveLoad;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Player;
import main.board.Board;

import java.io.*;

public class Console extends AnchorPane {

    /**
     * Chess board for this game.
     */
    private Board board;

    /**
     * Button to save this game.
     */
    private Button saveButton;

    /**
     * Button to load the saved game.
     */
    private Button loadButton;

    private VBox vBox;
    private HBox hBox;

    private Button colourTheme1;

    private Button colourTheme2;

    private Button colourTheme3;
    /**
     * Player using black pieces.
     */
    private Player blackPlayer;

    /**
     * Player using white pieces.
     */
    private Player whitePlayer;

    /**
     * Constructor of this Console.
     * @param board Chess board for this game
     * @param blackPlayer Player using black pieces
     * @param whitePlayer Player using white pieces
     */
    public Console(Board board, Player blackPlayer, Player whitePlayer) {
        this.board = board;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;

        vBox = new VBox();
        colourTheme1 = new Button("Colour Theme 1");
        colourTheme2 = new Button("Colour Theme 2");
        colourTheme3 = new Button("Colour Theme 3");
        vBox.getChildren().addAll(colourTheme1, colourTheme2, colourTheme3);

        hBox = new HBox();
        saveButton = new Button("Save");
        loadButton = new Button("Load");
        hBox.getChildren().addAll(saveButton, loadButton);

        getChildren().addAll(vBox, hBox);
    }

    public void initialSetup() {
        addVBox();
        addHBox();
    }

    private void addVBox() {
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(12);
        vBox.setStyle("-fx-background-color: #F00");

        setTopAnchor(vBox, 0.0);
    }

    private void addHBox() {
        saveButton.setOnMouseClicked(event -> saveGame());
        loadButton.setOnMouseClicked(event -> load());

        hBox.setPadding(new Insets(0, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #ff6");

        setBottomAnchor(hBox, 0.0);
    }

//
//    private void colourTheme1() {
//        board.
//    }

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

            board.getChildren().clear();
            blackPlayer.setPieces(saveFile.loadPieces(false));
            whitePlayer.setPieces(saveFile.loadPieces(true));
            board.drawBoard(blackPlayer, whitePlayer);
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
