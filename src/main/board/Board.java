package main.board;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Board {

    private GridPane pane;

    private int tileLength;

    private int boardLength;

    private Color color;

    public Board() {
        pane = new GridPane();
        buildBoard();
    }

    public void buildBoard() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                boolean white = (row + column) % 2 == 0;

                tileLength = 60;
                boardLength = tileLength * 8;

                pane.add(new Tile(column, row, tileLength, false, white), column, row);
            }
        }
    }

    public int getBoardLength() {
        return boardLength;
    }

    public GridPane getPane() {
        return pane;
    }

    public void setBlack() {
        color = Color.web("#231704");
    }

    public void setWhite() {
        color = Color.FLORALWHITE;
    }
}
