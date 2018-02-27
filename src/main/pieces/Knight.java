package main.pieces;

import java.io.Serializable;
import java.util.ArrayList;

public class Knight extends Piece implements Serializable {

    private final char type = '\u265E';

    public Knight(boolean white) {
        super(white);
        setText(Character.toString(type));
    }

    @Override
    public int[][] capturable(final int x, final int y) {
        return new int[0][0];
        // TODO Implement this method
    }

    @Override
    public int[][] movable(final int columnIndex, final int rowIndex) {
        System.out.println("KNIGHT MOVABLE CALLED");

        ArrayList<int[]> move = new ArrayList<>();

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
            }
        }

        int[][] result = move.toArray(new int[move.size()][2]);
        return result;
    }

}
