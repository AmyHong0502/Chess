package main.board;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    private int length;

    private boolean hasPiece;

    private boolean white;

    private boolean highlighted;

    public Tile(int length, boolean hasPiece, boolean white) {
        super(length, length);
        this.length = length;
        this.hasPiece = hasPiece;
        this.white = white;
        highlighted = false;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isHasPiece() {
        return hasPiece;
    }

    public void setHasPiece(boolean hasPiece) {
        this.hasPiece = hasPiece;
    }

    public boolean isWhite() {
        return white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }
}
