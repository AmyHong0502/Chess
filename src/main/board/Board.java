package main.board;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Board extends Application {

    private Group group;

    private GridPane pane;

    private int tileLength;

    private int boardLength;

    public Board() {
        group = new Group();
        pane = new GridPane();
        buildBoard();
        group.getChildren().addAll(pane);
    }

    /**
     * rows are called ranks (1 - 8), columns are called files (a - h)
     */
    public void buildBoard() {
        for (int rank = 0; rank < 8; rank++) {
            for (int file = 0; file < 8; file++) {
                boolean white = (rank + file) % 2 == 0;

                tileLength = 60;
                boardLength = tileLength * 8;

                pane.add(new Tile(file, rank, tileLength, false, white), file, rank);
            }
        }
    }

    public Group getGroup() {
        return group;
    }

    public int getBoardLength() {
        return boardLength;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(group, boardLength, boardLength));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
