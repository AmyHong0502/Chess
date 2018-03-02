package main.pieces;

public class Queen extends Piece {

    private final char type = '\u265B';

    public Queen(boolean white, int colIndex, int rowIndex) {
        super(white, '\u265B', colIndex, rowIndex);
        setText(Character.toString(type));
    }

    @Override
    public int[][] capturable() {
        return movable();
    }

    @Override
    public int[][] movable() {
        final int columnIndex = super.getColumnIndex();
        final int rowIndex = super.getRowIndex();

        int[][] bishop = new Bishop(white, super.getColumnIndex(), super.getRowIndex()).movable();
        int[][] rook = new Rook(white, super.getColumnIndex(), super.getRowIndex()).movable();

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
