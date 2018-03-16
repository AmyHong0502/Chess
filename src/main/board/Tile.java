package main.board;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private int verticalLevel;

    private int length;

    private boolean white;

    private boolean highlighted;

    public Tile(final int verticalLevel, int length, boolean white) {
        super(length, length);
        this.verticalLevel = verticalLevel;
        this.length = length;
        this.white = white;
        highlighted = false;
    }

    public int getVerticalLevel() { return verticalLevel; }

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
