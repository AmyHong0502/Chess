package main.pieces;

import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.Movement;

public abstract class Piece extends Text implements Movement {
    
    /** True if this piece belongs to the white player. */
    private final boolean white;

    /** Column index of this piece on the chessboard. */
    private int columnIndex;

    /** Row index of this piece on the chessboard. */
    private int rowIndex;

    /** Type of this piece: Pawn, Knight, Rook, Bishop, Queen, or King. */
    private char type;

    /** 
     * True if this piece never moved from the beginning of the current game.
     */
    private boolean neverMoved;

    /** True if this piece is highlighted. */
    private boolean highlighted;

    private int zLevel;

    Piece(final boolean white, final char type,
          int columnIndex, int rowIndex, int zLevel, boolean neverMoved) {
        super();
        this.white = white;
        this.type = type;
        this.neverMoved = neverMoved;
        highlighted = false;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.zLevel = zLevel;
    }

    public void initialSetup() {
        setFont(new Font(45));
        setTextAlignment(TextAlignment.CENTER);

        DropShadow ds = new DropShadow();
        ds.setOffsetX(3.0);
        ds.setOffsetY(3.0);
        ds.setColor(Color.web("0x889"));
        setEffect(ds);

        addMouseListener();
    }

    public void addMouseListener() {
        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> highlighted = !highlighted);
    }

    /**
     * Sets column index of this piece on the chess board.
     * @param colIndex column index of this piece on the chess board
     */
    public void setColumnIndex(int colIndex) {
        this.columnIndex = colIndex;
    }

    /**
     * Sets row index of this piece on the chess board.
     * @param rowIndex row index of this piece on the chess board
     */
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    /**
     * Returns column index of this piece on the chess board.
     * @return column index of this piece on the chess board
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * Returns row index of this piece on the chess board.
     * @return row index of this piece on the chess board
     */
    public int getRowIndex() {
        return rowIndex;
    }

    public char getType() {
        return type;
    }

    /**
     * Returns true if this piece belongs to the white player.
     * @return true if this piece belongs to the white player
     */
    public boolean isWhite() {
        return white;
    }

    public void setNeverMoved(boolean neverMoved) {
        this.neverMoved = neverMoved;
    }

    public boolean isNeverMoved() {
        return neverMoved;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setzLevel(int zLevel) {
        this.zLevel = zLevel;
    }

    public int getzLevel() {
        return zLevel;
    }
}
