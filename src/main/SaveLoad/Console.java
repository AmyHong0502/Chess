package main.SaveLoad;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.Player;
import main.pieces.Piece;

import java.io.*;
import java.util.ArrayList;

public class Console extends AnchorPane {

    Button saveButton;

    Button loadButton;

    HBox hbox;

    public Console(GridPane board) {
        saveButton = new Button("Save");
        loadButton = new Button("Load");
        hbox = new HBox();

        saveButton.setOnMouseClicked(event -> saveGame());
        loadButton.setOnMouseClicked(event -> load());

        getChildren().addAll(board, hbox);
    }

    private void addHBox() {
        hbox.setPadding(new Insets(0, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(saveButton, loadButton);
        hbox.setStyle("-fx-background-color: #ff6");
    }

    private void initialSetup() {
        AnchorPane.setRightAnchor(hbox, 10.0);
        AnchorPane.setBottomAnchor(hbox, 5.0);
    }

    private void savePieces(Save saveFile, ArrayList<Piece> pieces, boolean white) {
        ArrayList<PiecesSaver> readyToSave = new ArrayList<>();

        for (Piece p : pieces) {
            int col = p.getColumnIndex();
            int row = p.getRowIndex();
            char type = p.getType();

            readyToSave.add(new PiecesSaver(white, type, col, row));
        }

        if (white) {
            saveFile.setWhiteSavedPieces(readyToSave);
        } else {
            saveFile.setBlackSavedPieces(readyToSave);
        }
    }

    private void saveGame(Player blackPlayer, Player whitePlayer) {
        try {
            FileOutputStream f = new FileOutputStream("SaveChessGame.class");
            ObjectOutput out = new ObjectOutputStream(f);

            Save saveFile = new Save();
            savePieces(saveFile, blackPlayer.getPieces(), false);
            savePieces(saveFile, whitePlayer.getPieces(), true);

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
            FileInputStream fi = new FileInputStream("SaveChessGame.class");
            ObjectInput in = new ObjectInputStream(fi);

            Save saveFile = (Save) in.readObject();
            in.close();

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
