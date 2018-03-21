package me.amyhong.pieces;

public class Pawn extends Piece {

    /**
     * Black pawn character in Unicode. Black characters are used for design
     * purpose, regardless of actual colour of each piece.
     */
    private final char type = '\u265F';

    public Pawn(boolean white, int colIndex, int rowIndex, int verticalLevel) {
        super(white, '\u265F', colIndex, rowIndex, verticalLevel, false);
        setText(Character.toString(type));
    }

    public Pawn(boolean white, int colIndex, int rowIndex, int verticalLevel, boolean neverMoved) {
        super(white, '\u265F', colIndex, rowIndex, verticalLevel, neverMoved);
        setText(Character.toString(type));
    }

    int[][] movableThisBoard() {
        final int rowIndex = super.getRowIndex();
        final int columnIndex = super.getColumnIndex();

        if (rowIndex == 0 || rowIndex == 7) {
            return new int[0][0];
        }

        int[][] result;

        if (isNeverMoved()) {
            result = new int[2][3];

            for (int i = 0; i < 2; i++) {
                result[i][0] = columnIndex;
                result[i][1] = isWhite() ? rowIndex - (i + 1) : rowIndex + (i + 1);
                result[i][2] = super.getVerticalLevel();
            }
        } else {
            result = new int[1][3];
            result[0][0] = columnIndex;
            result[0][1] = isWhite() ? rowIndex - 1 : rowIndex + 1;
            result[0][2] = super.getVerticalLevel();
        }

        return result;
    }

    int[][] movableOtherBoards() {
        final int srcVerticalLevel = super.getVerticalLevel();
        return new int[0][];
    }

    @Override
    public int[][] capturable() {
        int[][] destination;

        final int rowIndex = super.getRowIndex();
        final int columnIndex = super.getColumnIndex();

        if (rowIndex == 0 || rowIndex == 7) {
            return new int[0][0];
        }

        if (isWhite()) {
            destination = new int[][]{{columnIndex - 1, rowIndex - 1},
                                      {columnIndex + 1, rowIndex - 1}};
        } else {
            destination = new int[][]{{columnIndex - 1, rowIndex + 1},
                                      {columnIndex + 1, rowIndex + 1}};
        }

        return destination;
    }

    int[][] movableUp(final int destVerticalLevel) {
        int[][] result = new int[1][2];
        if (isNeverMoved() && Math.abs(destVerticalLevel - super.getVerticalLevel()) == 2) {
            result[0][0] = super.getColumnIndex();
            result[0][1] = isWhite() ? super.getRowIndex() - 2 : super.getRowIndex() + 2;
        } else if (Math.abs(destVerticalLevel - super.getVerticalLevel()) == 1) {
            result[0][0] = super.getColumnIndex();
            result[0][1] = isWhite() ? super.getRowIndex() - 1 : super.getRowIndex() + 1;
        }
        return result;
    }

    @Override
    public int[][] searchPath(final int destColumnIndex, final int destRowIndex) {
        return new int[0][0];
    }
}
