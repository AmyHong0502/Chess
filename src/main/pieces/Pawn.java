package main.pieces;

public class Pawn extends Piece {

    private final char type = '\u265F';

    private boolean firstMove;

    public Pawn(boolean white, int colIndex, int rowIndex) {
        super(white, '\u265F', colIndex, rowIndex);
        setText(Character.toString(type));
        firstMove = true;
    }

    public void setFirstMove() {
        firstMove = false;
    }

    @Override
    public int[][] capturable() {
        int[][] destination;

        final int rowIndex = super.getRowIndex();
        final int columnIndex = super.getColumnIndex();

        if (rowIndex == 0 || rowIndex == 7) {
            return new int[0][0];
        }

        if (white) {
            destination = new int[][]{{columnIndex - 1, rowIndex - 1}, {columnIndex + 1, rowIndex - 1}};
        } else {
            destination = new int[][]{{columnIndex - 1, rowIndex + 1}, {columnIndex + 1, rowIndex + 1}};
        }

        return destination;
    }

    @Override
    public int[][] movable() {
        final int rowIndex = super.getRowIndex();
        final int columnIndex = super.getColumnIndex();

        if (rowIndex == 0 || rowIndex == 7) {
            return new int[0][0];
        }

        int[][] result;

        if (white) {
            if (firstMove) {
                result = new int[2][2];

                result[0][0] = columnIndex;
                result[0][1] = rowIndex - 1;
                result[1][0] = columnIndex;
                result[1][1] = rowIndex - 2;
            } else {
                result = new int[1][2];

                result[0][0] = columnIndex;
                result[0][1] = rowIndex - 1;
            }
        } else {
            if (firstMove) {
                result = new int[2][2];

                result[0][0] = columnIndex;
                result[0][1] = rowIndex + 1;
                result[1][0] = columnIndex;
                result[1][1] = rowIndex + 2;
            } else {
                result = new int[1][2];

                result[0][0] = columnIndex;
                result[0][1] = rowIndex + 1;
            }
        }

        return result;
    }

}
