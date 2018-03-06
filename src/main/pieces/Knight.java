package main.pieces;

import java.util.ArrayList;

public class Knight extends Piece {

    private final char type = '\u265E';

    public Knight(boolean white, int colIndex, int rowIndex) {
        super(white, '\u265E', colIndex, rowIndex, true);
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

        move.add(new int[]{columnIndex - 1, rowIndex - 2});
        move.add(new int[]{columnIndex + 1, rowIndex - 2});
        move.add(new int[]{columnIndex + 2, rowIndex - 1});
        move.add(new int[]{columnIndex + 2, rowIndex + 1});
        move.add(new int[]{columnIndex + 1, rowIndex + 2});
        move.add(new int[]{columnIndex - 1, rowIndex + 2});
        move.add(new int[]{columnIndex - 2, rowIndex + 1});
        move.add(new int[]{columnIndex - 2, rowIndex - 1});

        for (int i = 0; i < move.size(); i++) {
            if (move.get(i)[0] < 0 || move.get(i)[0] > 7 || move.get(i)[1] < 0 || move.get(i)[1] > 7) {
                move.remove(i);
                i--;
            }
        }

        return move.toArray(new int[move.size()][2]);
    }

    @Override
    public int[][] searchPath(final int destColumnIndex, final int destRowIndex) {
        return new int[0][0];
    }

}
