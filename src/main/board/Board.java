package main.board;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import main.pieces.Pawn;

public class Board extends GridPane {

    private int tileLength;

    private int boardLength;

    private Color color;

    public Board() {
        buildBoard();
    }

    public void buildBoard() {
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                boolean white = (row + column) % 2 == 0;

                tileLength = 60;
                boardLength = tileLength * 8;

                add(new Tile(column, row, tileLength, false, white), column, row);
            }
        }
    }

    public int getBoardLength() {
        return boardLength;
    }

    public void setBlack() {
        color = Color.web("#231704");
    }

    public void setWhite() {
        color = Color.FLORALWHITE;
    }

    public void highlightMovable(Pawn piece, int x, int y) {
        int[][] movable = piece.movable(x, y);

    }
}
