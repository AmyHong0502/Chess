package main.pieces;

import java.util.ArrayList;

public class King extends Piece {

    /**
     * Black king character in Unicode. Black characters are used for design
     * purpose, regardless of actual colour of each piece.
     */
    private final char type = '\u265A';

    public King(boolean white, int colIndex, int rowIndex, int zLevel) {
        super(white, '\u265A', colIndex, rowIndex, zLevel, true);
        setText(Character.toString(type));
    }

    public King(boolean white, int colIndex, int rowIndex,
                                     int zLevel, boolean neverMoved) {
        super(white, '\u265A', colIndex, rowIndex, zLevel, neverMoved);
        setText(Character.toString(type));
    }

    @Override
    public int[][] capturable() {
        return movable();
    }

    @Override
    public int[][] movable() {
        ArrayList<int[]> move = new ArrayList<>();
        final int columnIndex = super.getColumnIndex();
        final int rowIndex = super.getRowIndex();

        if (rowIndex != 7) {
            move.add(new int[]{columnIndex, rowIndex + 1});

            if (columnIndex != 7) {
                move.add(new int[]{columnIndex + 1, rowIndex + 1});
            }
            if (columnIndex != 0) {
                move.add(new int[]{columnIndex - 1, rowIndex + 1});
            }
        }

        if (rowIndex != 0) {
            move.add(new int[]{columnIndex, rowIndex - 1});

            if (columnIndex != 7) {
                move.add(new int[]{columnIndex + 1, rowIndex - 1});
            }
            if (columnIndex != 0) {
                move.add(new int[]{columnIndex - 1, rowIndex - 1});
            }
        }

        if (columnIndex != 7) {
            move.add(new int[]{columnIndex + 1, rowIndex});
        }

        if (columnIndex != 0) {
            move.add(new int[]{columnIndex - 1, rowIndex});
        }

        return move.toArray(new int[move.size()][2]);
    }

    @Override
    public int[][] searchPath(final int destColumnIndex, final int destRowIndex) {
        return new int[0][0];
    }

}
