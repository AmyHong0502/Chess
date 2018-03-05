package main.SaveLoad;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import main.Player;
import main.board.Board;

import java.io.*;

public class Console extends AnchorPane {

    private Board board;

    private Button saveButton;

    private Button loadButton;

    private HBox hbox;

    private Player blackPlayer;

    private Player whitePlayer;

    public Console(Board board, Player blackPlayer, Player whitePlayer) {
        this.board = board;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        saveButton = new Button("Save");
        loadButton = new Button("Load");
        hbox = new HBox();
        getChildren().addAll(board, hbox);
    }

    private void addHBox() {
        saveButton.setOnMouseClicked(event -> saveGame());
        loadButton.setOnMouseClicked(event -> load());

        hbox.setPadding(new Insets(0, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(saveButton, loadButton);
        hbox.setStyle("-fx-background-color: #ff6");
    }

    public void initialSetup() {
        addHBox();
        setRightAnchor(hbox, 10.0);
        setBottomAnchor(hbox, 5.0);
    }

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
