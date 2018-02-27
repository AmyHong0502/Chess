package main.pieces;

import java.io.Serializable;

public class Pawn extends Piece implements Serializable {

    private final char type = '\u265F';

    private boolean firstMove;

    public Pawn(boolean white) {
        super(white);
        setText(Character.toString(type));
        firstMove = true;
    }

    @Override
    public int[][] capturable(final int x, final int y) {
        int[][] destination;

        if (y == 0) {
            return new int[0][0];
        }

        destination = new int[][]{{x - 1, y - 1}, {x + 1, y - 1}};
        return destination;
    }

    @Override
    public int[][] movable(final int columnIndex, final int rowIndex) {
        if (rowIndex == 0) {
            return new int[0][0];
        }

        int[][] result;

        if (white) {
            if (firstMove) {
                System.out.println("FIRSTMOVE");
                result = new int[2][2];

                result[0][0] = columnIndex;
                result[0][1] = rowIndex - 1;
                result[1][0] = columnIndex;
                result[1][1] = rowIndex - 2;
            } else {
                System.out.println("NOT FIRSTMOVE");
                result = new int[1][2];

                result[0][0] = columnIndex;
                result[0][1] = rowIndex - 1;
            }
        } else {
            if (firstMove) {
                System.out.println("FIRSTMOVE");
                result = new int[2][2];

                result[0][0] = columnIndex;
                result[0][1] = rowIndex + 1;
                result[1][0] = columnIndex;
                result[1][1] = rowIndex + 2;
            } else {
                System.out.println("NOT FIRSTMOVE");
                result = new int[1][2];

                result[0][0] = columnIndex;
                result[0][1] = rowIndex + 1;
            }
        }
        
        return result;
    }

}
