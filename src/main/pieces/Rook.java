package main.pieces;

import main.board.Board;

import java.io.Serializable;

public class Rook extends Piece implements Serializable {

    private final char type = '\u265C';

    private boolean firstMove;

    public Rook(boolean white) {
        super(white);
        setText(Character.toString(type));
        firstMove = true;
    }

    @Override
    public int[][] capturable(final int x, final int y) {
        return new int[0][0];
        // TODO Implement this method
    }

    @Override
    public int[][] movable(final int columnIndex, final int rowIndex) {
        System.out.println("ROOK MOVABLE CALLED");

        int[][] result = new int[(Board.NUMBER_OF_CELLS - 1) * 2][2];

        int cellCount = 0;

        for (int row = 0; row < Board.NUMBER_OF_CELLS; row++) {
            if (row == rowIndex) {
                continue;
            }
            result[cellCount][0] = columnIndex;
            result[cellCount][1] = row;
            cellCount++;
        }

        for (int col = 0; col < Board.NUMBER_OF_CELLS; col++) {
            if (col == columnIndex) {
                continue;
            }
            result[cellCount][0] = col;
            result[cellCount][1] = rowIndex;
            cellCount++;
        }

        return result;
    }

}
