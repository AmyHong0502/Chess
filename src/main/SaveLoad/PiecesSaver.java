package main.SaveLoad;

import java.io.Serializable;

public class PiecesSaver implements Serializable {
    private final int columnIndex;
    private final int rowIndex;
    private final char type;
    private final boolean white;

    PiecesSaver(boolean white, char type, int columnIndex, int rowIndex) {
        this.white = white;
        this.type = type;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
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
}