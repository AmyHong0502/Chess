package main.SaveLoad;

import java.io.Serializable;

public class PiecesSaver implements Serializable {
    private final int columnIndex;
    private final int rowIndex;
    private final char type;
    private final boolean white;
    private final boolean firstMove;

    PiecesSaver(boolean white, char type, int columnIndex, int rowIndex, boolean firstMove) {
        this.white = white;
        this.type = type;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.firstMove = firstMove;
    }

    public char getType() {
        return type;
    }

    public boolean isWhite() {
        return white;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public boolean isFirstMove() { return firstMove; }
}