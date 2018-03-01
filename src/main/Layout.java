package main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.board.Board;

import java.io.*;

public class Layout extends BorderPane {

    private Board board;

    private Player player1;

    private Player player2;

    Button saveButton;

    Button loadButton;

    Layout() {
        player1 = new Player(false);
        player2 = new Player(true);
        board = new Board(player1, player2);
        board.setStyle("-fx-background-color: #066;");

        addHBox(board);
        this.setCenter(board);
    }

    private void addHBox(GridPane board) {
        saveButton = new Button("Save");
        loadButton = new Button("Load");

        saveButton.setOnMouseClicked(event -> save());
        loadButton.setOnMouseClicked(event -> load());

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 10, 10, 10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(saveButton, loadButton);

        board.add(hbox, 8, 0);
    }

    private void save() {
        try {
            FileOutputStream f = new FileOutputStream("SaveChessGame.txt");
            ObjectOutput out = new ObjectOutputStream(f);

            out.writeObject(board);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        System.out.println("Saved.");
    }

    private void load() {
        try {
            FileInputStream fi = new FileInputStream("SaveChessGame.txt");
            ObjectInput in = new ObjectInputStream(fi);
            board = (Board) in.readObject();
            in.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {

        }
        System.out.println("Loaded.");
    }
}
