package main.board;

import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    /** Vertical-3D level of the board where this tile is located. */
    private int verticalLevel;

    /** Length of this tile. */
    private int length;

    /** True if this tile's colour is light. */
    private boolean white;

    /** True if this tile is highlighted. */
    private boolean highlighted;

    /**
     * Constructor of this Tile.
     * 
     * @param verticalLevel vertical-3D level of the board 
     *                      where this tile is located
     * @param length        length of this tile
     * @param white         true if this tile's colour is light
     */
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
