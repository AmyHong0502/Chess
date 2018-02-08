package main.pieces;

public class Queen extends Piece {

    private final char type = '\u265B';

    private boolean firstMove;

    public Queen(boolean white) {
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
        System.out.println("QUEEN MOVABLE CALLED");

        int[][] bishop = new Bishop(white).movable(columnIndex, rowIndex);
        int[][] rook = new Rook(white).movable(columnIndex, rowIndex);

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

}
