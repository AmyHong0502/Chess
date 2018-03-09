package main.pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class Queen extends Piece {

    /** 
     * Black queen character in Unicode. Black characters are used for design 
     * purpose, regardless of actual colour of each piece.
     */
    private final char type = '\u265B';

    public Queen(boolean white, int colIndex, int rowIndex,
                                                        final int colourTheme) {
        super(white, '\u265B', colIndex, rowIndex, true, colourTheme);
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

        int[][] bishop = new Bishop(isWhite(), columnIndex,
                rowIndex, 1).movable();
        int[][] rook = new Rook(isWhite(), columnIndex,
                rowIndex, 1).movable();

        int resultSize = bishop.length + rook.length;
        int[][] result = new int[resultSize][2];

        for (int i = 0; i < bishop.length; i++) {
            result[i][0] = bishop[i][0];
            result[i][1] = bishop[i][1];
        }

        for (int i = 0; i < rook.length; i++) {
            result[i + bishop.length][0] = rook[i][0];
            result[i + bishop.length][1] = rook[i][1];
        }

        return result;
    }

    @Override
    public int[][] searchPath(final int destColumnIndex, 
                                                       final int destRowIndex) {
        final int columnIndex = super.getColumnIndex();
        final int rowIndex = super.getRowIndex();

        ArrayList<int[]> bishop = new ArrayList<>(Arrays.asList(
               new Bishop(isWhite(), columnIndex, rowIndex, 1)
                                   .searchPath(destColumnIndex, destRowIndex)));
        ArrayList<int[]> rook = new ArrayList<>(Arrays.asList(
                 new Rook(isWhite(), columnIndex, rowIndex, 1)
                                   .searchPath(destColumnIndex, destRowIndex)));

        bishop.addAll(rook);
        return bishop.toArray(new int[bishop.size()][2]);
    }
}
