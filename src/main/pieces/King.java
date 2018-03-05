package main.pieces;

import java.util.ArrayList;

public class King extends Piece {

    private final char type = '\u265A';

    public King(boolean white, int colIndex, int rowIndex) {
        super(white, '\u265A', colIndex, rowIndex, true);
        setText(Character.toString(type));
        firstMove = true;
    }

    public King(boolean white, int colIndex, int rowIndex, boolean firstMove) {
        super(white, '\u265A', colIndex, rowIndex, firstMove);
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
