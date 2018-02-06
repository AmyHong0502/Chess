package main.board;

import javafx.scene.layout.GridPane;

public class Board {

    private GridPane pane;

    private int tileLength;

    private int boardLength;

    public Board() {
        pane = new GridPane();
        buildBoard();
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

    public int getBoardLength() {
        return boardLength;
    }

    public GridPane getPane() {
        return pane;
    }

}
