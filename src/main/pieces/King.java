package main.pieces;

import java.io.Serializable;
import java.util.ArrayList;

public class King extends Piece implements Serializable {

    private final char type = '\u265A';

    private boolean firstMove;

    public King(boolean white, int colIndex, int rowIndex) {
        super(white, colIndex, rowIndex);
        setText(Character.toString(type));
        firstMove = true;
    }

    @Override
    public int[][] capturable(final int columnIndex, final int rowIndex) {
        return movable(columnIndex, rowIndex);
    }

    @Override
    public int[][] movable(final int columnIndex, final int rowIndex) {
        ArrayList<int[]> move = new ArrayList<>();

        if (rowIndex != 7) {
            move.add(new int[] {columnIndex, rowIndex + 1});

            if (columnIndex != 7) {
                move.add(new int[] {columnIndex + 1, rowIndex + 1});
            }
            if (columnIndex != 0) {
                move.add(new int[] {columnIndex - 1, rowIndex + 1});
            }
        }

        if (rowIndex != 0) {
            move.add(new int[] {columnIndex, rowIndex - 1});

            if (columnIndex != 7) {
                move.add(new int[] {columnIndex + 1, rowIndex - 1});
            }
            if (columnIndex != 0) {
                move.add(new int[] {columnIndex - 1, rowIndex - 1});
            }
        }

        if (columnIndex != 7) {
            move.add(new int[] {columnIndex + 1, rowIndex});
        }

        if (columnIndex != 0) {
            move.add(new int[] {columnIndex - 1, rowIndex});
        }

        return move.toArray(new int[move.size()][2]);
    }

}
