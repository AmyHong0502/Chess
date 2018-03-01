package main.pieces;

import java.io.Serializable;

public class Queen extends Piece implements Serializable {

    private final char type = '\u265B';

    public Queen(boolean white, int colIndex, int rowIndex) {
        super(white, colIndex, rowIndex);
        setText(Character.toString(type));
    }

    @Override
    public int[][] capturable(final int columnIndex, final int rowIndex) {
        return movable(columnIndex, rowIndex);
    }

    @Override
    public int[][] movable(final int columnIndex, final int rowIndex) {
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
