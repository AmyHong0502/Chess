package main.pieces;

import main.board.Board;

import java.util.ArrayList;
import java.util.Arrays;

public class Bishop extends Piece {

    /**
     * Black bishop character in Unicode. Black characters are used for design
     * purpose, regardless of actual colour of each piece.
     */
    private final char type = '\u265D';

    /**
     * Constructor of this Bishop.
     *
     * @param white       true if this piece belongs to the white player.
     * @param columnIndex this piece's column index on the Board.
     * @param rowIndex    this piece's row index on the Board.
     */
    public Bishop(boolean white, int columnIndex, int rowIndex, int verticalLevel) {
        super(white, '\u265D', columnIndex, rowIndex, verticalLevel, false);
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

        final int neSize = northToSouthCount < eastToWestCount
                ? northToSouthCount : eastToWestCount;
        final int seSize = southToNorthCount < eastToWestCount
                ? southToNorthCount : eastToWestCount;
        final int swSize = southToNorthCount < westToEastCount
                ? southToNorthCount : westToEastCount;
        final int nwSize = northToSouthCount < westToEastCount
                ? northToSouthCount : westToEastCount;

        ArrayList<int[]> northEast = new ArrayList<>(Arrays.asList(
                movableNE(neSize, columnIndex, rowIndex)));
        ArrayList<int[]> southEast = new ArrayList<>(Arrays.asList(
                movableSE(seSize, columnIndex, rowIndex)));
        ArrayList<int[]> southWest = new ArrayList<>(Arrays.asList(
                movableSW(swSize, columnIndex, rowIndex)));
        ArrayList<int[]> northWest = new ArrayList<>(Arrays.asList(
                movableNW(nwSize, columnIndex, rowIndex)));

        ArrayList<int[]> result = new ArrayList<>();
        result.addAll(northEast);
        result.addAll(southEast);
        result.addAll(southWest);
        result.addAll(northWest);

        return result.toArray(new int[result.size()][2]);
    }

    private int[][] movableNE(final int size, final int columnIndex, final int rowIndex) {
        int col = columnIndex;
        int row = rowIndex;
        int[][] result = new int[size][2];

        for (int count = 0; count < size; count++) {
            col++;
            result[count][0] = col;
            row--;
            result[count][1] = row;
        }

        return result;
    }

    private int[][] movableSE(final int size, final int columnIndex, final int rowIndex) {
        int col = columnIndex;
        int row = rowIndex;
        int[][] result = new int[size][2];

        for (int count = 0; count < size; count++) {
            col++;
            result[count][0] = col;
            row++;
            result[count][1] = row;
        }

        return result;
    }

    private int[][] movableSW(final int size, final int columnIndex, final int rowIndex) {
        int col = columnIndex;
        int row = rowIndex;
        int[][] result = new int[size][2];

        for (int count = 0; count < size; count++) {
            col--;
            result[count][0] = col;
            row++;
            result[count][1] = row;
        }

        return result;
    }

    private int[][] movableNW(final int size, final int columnIndex, final int rowIndex) {
        int col = columnIndex;
        int row = rowIndex;
        int[][] result = new int[size][2];

        for (int count = 0; count < size; count++) {
            col--;
            result[count][0] = col;
            row--;
            result[count][1] = row;
        }

        return result;
    }

    @Override
    public int[][] searchPath(final int destColumnIndex, final int destRowIndex) {
        final int srcColumnIndex = super.getColumnIndex();
        final int srcRowIndex = super.getRowIndex();

        final boolean northEast = (srcColumnIndex < destColumnIndex)
                && (srcRowIndex > destRowIndex);
        final boolean southEast = (srcColumnIndex < destColumnIndex)
                && (srcRowIndex < destRowIndex);
        final boolean southWest = (srcColumnIndex > destColumnIndex)
                && (srcRowIndex < destRowIndex);
        final boolean northWest = (srcColumnIndex > destColumnIndex)
                && (srcRowIndex > destRowIndex);

        int colSize = Math.abs(srcColumnIndex - destColumnIndex);
        int rowSize = Math.abs(srcRowIndex - destRowIndex);

        int resultSize = colSize == rowSize ? colSize : 0;

        if (northEast) {
            return movableNE(resultSize, srcColumnIndex, srcRowIndex);
        }
        if (southEast) {
            return movableSE(resultSize, srcColumnIndex, srcRowIndex);
        }
        if (southWest) {
            return movableSW(resultSize, srcColumnIndex, srcRowIndex);
        }
        if (northWest) {
            return movableNW(resultSize, srcColumnIndex, srcRowIndex);
        }

        return new int[0][0];
    }


}
