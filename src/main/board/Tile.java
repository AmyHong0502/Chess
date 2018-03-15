package main.board;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private int zLevel;

    private int length;

    private boolean white;

    private boolean highlighted;

    public Tile(final int zLevel, int length, boolean hasPiece, boolean white) {
        super(length, length);
        this.zLevel = zLevel;
        this.length = length;
        this.white = white;
        highlighted = false;
    }

    public int getzLevel() { return zLevel; }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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
