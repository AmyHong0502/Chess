package main.pieces;

public class Pawn extends main.pieces.Piece {

    private final char type = '\u265F';

    private boolean firstMove;

    public Pawn(boolean white) {
        super(white);
        setText(Character.toString(type));
        firstMove = true;
    }

    @Override
    public int[][] capturable(final int x, final int y) {
        return new int[0][0];
        // TODO Implement this method
    }

    /**
     * Returns possible positions for move to catch the enemy's pieces.
     * The result doesn't mean the player can catch it. It means this piece can move to the place.
     *
     * @return
     */
    /*
    @Override
    public int[][] capturable() {
        int[][] destination;

        if (y == 0) {
            return new int[0][0];
        }

        destination = new int[][]{{x - 1, y - 1}, {x + 1, y - 1}};
        return destination;
    }
*/

    @Override
    public int[][] movable(final int x, final int y) {
        System.out.println("START MOVABLE");
        if (y == 0) {
            return new int[0][0];
        }

        int[][] result;

        if (firstMove) {
            System.out.println("FIRSTMOVE");
            result = new int[2][2];

            result[0][0] = x;
            result[0][1] = y - 1;
            result[1][0] = x;
            result[1][1] = y - 2;
        } else {
            System.out.println("NOT FIRSTMOVE");
            result = new int[1][2];

            result[0][0] = x;
            result[0][1] = y - 1;
        }

        return result;
    }

}
