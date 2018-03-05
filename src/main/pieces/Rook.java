package main.pieces;

import main.board.Board;

public class Rook extends Piece {

    private final char type = '\u265C';

    public Rook(boolean white, int colIndex, int rowIndex) {
        super(white, '\u265C', colIndex, rowIndex, true);
        setText(Character.toString(type));
        firstMove = true;
    }

    public Rook(boolean white, int colIndex, int rowIndex, boolean firstMove) {
        super(white, '\u265C', colIndex, rowIndex, firstMove);
        setText(Character.toString(type));
    }

    @Override
    public int[][] capturable() {
        return movable();
    }

    @Override
    public int[][] movable() {
        int[][] result = new int[(Board.NUMBER_OF_CELLS - 1) * 2][2];
        int cellCount = 0;
        final int columnIndex = super.getColumnIndex();
        final int rowIndex = super.getRowIndex();

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
