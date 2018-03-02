package main.pieces;

import main.board.Board;

public class Bishop extends Piece {

    private final char type = '\u265D';

    public Bishop(boolean white, int colIndex, int rowIndex) {
        super(white, '\u265D', colIndex, rowIndex);
        setText(Character.toString(type));
    }

    @Override
    public int[][] capturable() {
        return movable();
    }

    @Override
    public int[][] movable() {
        final int columnIndex = super.getColumnIndex();
        final int rowIndex = super.getRowIndex();

        final int northToSouthCount = rowIndex;
        final int southToNorthCount = Board.NUMBER_OF_CELLS - rowIndex - 1;
        final int westToEastCount = columnIndex;
        final int eastToWestCount = Board.NUMBER_OF_CELLS - columnIndex - 1;

        final int neSize = northToSouthCount < eastToWestCount ? northToSouthCount : eastToWestCount;
        final int seSize = southToNorthCount < eastToWestCount ? southToNorthCount : eastToWestCount;
        final int swSize = southToNorthCount < westToEastCount ? southToNorthCount : westToEastCount;
        final int nwSize = northToSouthCount < westToEastCount ? northToSouthCount : westToEastCount;

        final int resultSize = neSize + seSize + swSize + nwSize;
        int[][] result = new int[resultSize][2];

        int col;
        int row;

        // north-east
        col = columnIndex;
        row = rowIndex;
        for (int count = 0; count < neSize; count++) {
            col++;
            result[count][0] = col;
            row--;
            result[count][1] = row;
        }

        // south-east
        col = columnIndex;
        row = rowIndex;
        for (int count = 0; count < seSize; count++) {
            col++;
            result[count + neSize][0] = col;
            row++;
            result[count + neSize][1] = row;
        }

        // south-west
        col = columnIndex;
        row = rowIndex;
        for (int count = 0; count < swSize; count++) {
            col--;
            result[count + neSize + seSize][0] = col;
            row++;
            result[count + neSize + seSize][1] = row;
        }

        // north-west
        col = columnIndex;
        row = rowIndex;
        for (int count = 0; count < nwSize; count++) {
            col--;
            result[count + neSize + seSize + swSize][0] = col;
            row--;
            result[count + neSize + seSize + swSize][1] = row;
        }

        return result;
    }

}
