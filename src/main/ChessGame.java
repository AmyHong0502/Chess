package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.board.Board;

public class ChessGame extends Application {

    private Group root;

    private Board board;

    private Player player1;

    private Player player2;

    private int boardLength;

    public ChessGame() {
        root = new Group();
        board = new Board();
        player1 = new Player(false);
        player2 = new Player(true);

        boardLength = board.getBoardLength();

        root.getChildren().addAll(board);
        board.initializePieces(player1, player2);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, boardLength, boardLength));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
