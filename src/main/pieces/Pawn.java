package main.pieces;

public class Pawn extends main.pieces.Piece {

    private boolean firstmove;

    public Pawn(int x, int y, boolean white) {
        super(white);
        super.setXCoordinate(x);
        super.setYCoordinate(y);
        setType(super.white);
        firstmove = true;
    }

    private void setType(boolean white) {
        char result;

        final char blackPawn = '\u265F';
        final char whitePawn = '\u2659';

        result = white ? whitePawn : blackPawn;

        super.setType(result);
    }

    @Override
    public int[][] movable() {
        int destination;
        int[][] result;

        destination = firstmove ? 2 : 1;

        int x = super.xCoordinate;
        int y = super.yCoordinate - destination;

        if (y == 0) {
            result = new int[0][0];
        } else {
            result = new int[1][2];
            result[0][0] = x;
            result[0][1] = y;
        }

        return result;
    }

    /**
     * Returns possible positions for move to catch the enemy's pieces.
     * The result doesn't mean the player can catch it. It means this piece can move to the place.
     *
     * @return
     */
    @Override
    public int[][] catchable() {
        int[][] destination;

        int x = super.getxCoordinate();
        int y = super.getyCoordinate();

        if (y == 0) {
            return new int[0][0];
        }

        destination = new int[][] {{x - 1, y - 1}, {x + 1, y - 1}};
        return destination;
    }

}
