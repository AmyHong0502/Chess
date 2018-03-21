package me.amyhong.board;

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

    /**
     * Returns vertical level of a board where this tile is located.
     *
     * @return vertical level of a board where this tile is located
     */
    public int getVerticalLevel() { return verticalLevel; }

    /**
     * Returns length of this tile.
     *
     * @return length of this tile
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets length of this tile.
     *
     * @param length length of this tile
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Returns true if this tile is white.
     *
     * @return true if this tile is white
     */
    public boolean isWhite() {
        return white;
    }

    /**
     * Sets if this tile's colour is white or black.
     *
     * @param white true if this tile is white
     */
    public void setWhite(boolean white) {
        this.white = white;
    }

    /**
     * Returns true if this tile is highlighted.
     *
     * @return true if this tile is highlighted
     */
    public boolean isHighlighted() {
        return highlighted;
    }

    /**
     * Sets if this tile is highlighted or not.
     *
     * @param highlighted true if this tile is highlighted
     */
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }
}
