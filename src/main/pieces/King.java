package main.pieces;

import java.io.Serializable;
import java.util.ArrayList;

public class King extends Piece implements Serializable {

    private final char type = '\u265A';

    private boolean firstMove;

    public King(boolean white) {
        super(white);
        setText(Character.toString(type));
        firstMove = true;
    }

    @Override
    public int[][] capturable(final int x, final int y) {
        return new int[0][0];
        // TODO Implement this method
    }

    @Override
    public int[][] movable(final int columnIndex, final int rowIndex) {
        ArrayList<int[]> availability = new ArrayList<>();

        if (rowIndex != 7) {
            int[] s = {columnIndex, rowIndex + 1};
            availability.add(s);

            if (columnIndex != 7) {
                int[] se = {columnIndex + 1, rowIndex + 1};
                availability.add(se);
            }
            if (columnIndex != 0) {
                int[] sw = {columnIndex - 1, rowIndex + 1};
                availability.add(sw);
            }
        }

        if (rowIndex != 0) {
            int[] n = {columnIndex, rowIndex - 1};
            availability.add(n);

            if (columnIndex != 7) {
                int[] ne = {columnIndex + 1, rowIndex - 1};
                availability.add(ne);
            }
            if (columnIndex != 0) {
                int[] nw = {columnIndex - 1, rowIndex - 1};
                availability.add(nw);
            }
        }

        if (columnIndex != 7) {
            int[] e = {columnIndex + 1, rowIndex};
            availability.add(e);
        }

        if (columnIndex != 0) {
            int[] w = {columnIndex - 1, rowIndex};
            availability.add(w);
        }

        int[][] result = new int[availability.size()][2];

        for (int i = 0; i < availability.size(); i++) {
            result[i][0] = availability.get(i)[0];
            result[i][1] = availability.get(i)[1];
        }

        return result;
    }

}
