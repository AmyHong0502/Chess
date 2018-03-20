package main.pieces;

import main.board.Board;

public class Rook extends Piece {

    /** 
     * Black rook character in Unicode. Black characters are used for design 
     * purpose, regardless of actual colour of each piece.
     */
    private final char type = '\u265C';

    public Rook(boolean white, int colIndex, int rowIndex, int zLevel) {
        super(white, '\u265C', colIndex, rowIndex, zLevel, true);
        setText(Character.toString(type));
    }

    public Rook(boolean white, int colIndex, int rowIndex, int verticalLevel, boolean neverMoved) {
        super(white, '\u265C', colIndex, rowIndex, verticalLevel, neverMoved);
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

    private int[][] movableHorizontal(final int destColumnIndex, 
                                                       final int destRowIndex) {
        final int srcColumnIndex = super.getColumnIndex();
        final int srcRowIndex = super.getRowIndex();

        if (srcRowIndex != destRowIndex) {
            return new int[0][0];
        }

        int[][] result = new int[Math.abs(srcColumnIndex - destColumnIndex)][2];;
        int cellCount = 0;

        if (srcColumnIndex > destColumnIndex) {
            for (int col = destColumnIndex; col < srcColumnIndex; col++) {
                result[cellCount][0] = col;
                result[cellCount][1] = srcRowIndex;
                cellCount++;
            }
        } else {
            for (int col = srcColumnIndex + 1; col <= destColumnIndex; col++) {
                result[cellCount][0] = col;
                result[cellCount][1] = srcRowIndex;
                cellCount++;
            }
        }
        return result;
    }

    private int[][] movableVertical(final int destColumnIndex, 
                                                       final int destRowIndex) {
        final int srcColumnIndex = super.getColumnIndex();
        final int srcRowIndex = super.getRowIndex();

        if (srcColumnIndex != destColumnIndex) {
            return new int[0][0];
        }

        int[][] result = new int[Math.abs(srcRowIndex - destRowIndex)][2];
        int cellCount = 0;

        if (srcRowIndex > destRowIndex) {
            for (int row = destRowIndex; row < srcRowIndex; row++) {
                result[cellCount][0] = srcColumnIndex;
                result[cellCount][1] = row;
                cellCount++;
            }
        } else {
            for (int row = srcRowIndex + 1; row <= destRowIndex; row++) {
                result[cellCount][0] = srcColumnIndex;
                result[cellCount][1] = row;
                cellCount++;
            }
        }

        return result;
    }

    @Override
    public int[][] searchPath(final int destColumnIndex, 
                                                       final int destRowIndex) {
        final int srcColumnIndex = super.getColumnIndex();
        final int srcRowIndex = super.getRowIndex();

        if (srcColumnIndex == destColumnIndex) {
            return movableVertical(destColumnIndex, destRowIndex);
        }
        if (srcRowIndex == destRowIndex) {
            return movableHorizontal(destColumnIndex, destRowIndex);
        }

        return new int[0][0];
    }

}
