package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.board.Board;

public class ChessGame extends Application {

    private Group root;

    private Board board;

    private GridPane pane;
/*
    Player player1;

    Player player2;
*/
    private int boardLength;

    public ChessGame() {
        root = new Group();
        boardLength = 430;
        pane = new GridPane();

        root.getChildren().addAll(pane);


        board = new Board();
        pane = board.getPane();

        boardLength = board.getBoardLength();

        root.getChildren().addAll(pane);
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
