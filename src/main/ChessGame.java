package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.board.Board;

import java.io.Serializable;

public class ChessGame extends Application implements Serializable {

    private Group root;

    private Board board;

    private Player player1;

    private Player player2;

    public ChessGame() {
        root = new Group();
        player1 = new Player(false);
        player2 = new Player(true);
        board = new Board(player1, player2);
        root.getChildren().addAll(board);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, board.getBoardLength(), board.getBoardLength()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
