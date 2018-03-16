package main.pieces;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.Movement;

public abstract class Piece extends Text implements Movement {
    
    /** True if this piece belongs to the white player. */
    private final boolean white;

    /** Type of this piece: Pawn, Knight, Rook, Bishop, Queen, or King. */
    private char type;
    
    /** Column index of this piece on the chessboard. */
    private int columnIndex;

    /** Row index of this piece on the chessboard. */
    private int rowIndex;

    /** True if this piece never moved from the beginning of the current game. */
    private boolean neverMoved;

    /** True if this piece is highlighted. */
    private boolean highlighted;

    /** vertical-3D level of the board where this piece is located. */
    private int verticalLevel;

    /**
     * Constructor of this Piece.
     * @param white         True if this piece belongs to the white player
     * @param type          Type of this piece: 
     *                      Pawn, Knight, Rook, Bishop, Queen, or King
     * @param columnIndex   Column index of this piece on the chessboard
     * @param rowIndex      Row index of this piece on the chessboard
     * @param verticalLevel vertical-3D level of the board
     *                      where this piece is located
     * @param neverMoved    True if this piece never moved 
     *                      from the beginning of the current game
     */
    Piece(final boolean white, final char type,
          int columnIndex, int rowIndex, int verticalLevel, boolean neverMoved) {
        super();
        this.white = white;
        this.type = type;
        this.neverMoved = neverMoved;
        highlighted = false;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.verticalLevel = verticalLevel;
    }

    public void initialSetup() {
        setFont(new Font(45));
        setTextAlignment(TextAlignment.CENTER);

        DropShadow ds = new DropShadow();
        ds.setOffsetX(3.0);
        ds.setOffsetY(3.0);
        ds.setColor(Color.web("0x889"));
        setEffect(ds);
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

    /**
     * Returns type of this piece: Pawn, Knight, Rook, Bishop, Queen, or King.
     * @return type of this piece: Pawn, Knight, Rook, Bishop, Queen, or King
     */
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

    /**
     * Sets whether this piece has moved or not during this game.
     * @param neverMoved true if this piece never moved 
     *                   from the beginning of the current game
     */
    public void setNeverMoved(boolean neverMoved) {
        this.neverMoved = neverMoved;
    }

    /**
     * Returns true if this piece never moved from the beginning of the 
     * current game.
     *         
     * @return true if this piece never moved from the beginning of the 
     *         current game
     */
    public boolean isNeverMoved() {
        return neverMoved;
    }

    /**
     * Sets whether this piece is highlighted or not.
     * 
     * @param highlighted true if this piece is highlighted
     */
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    /**
     * Returns true if this piece is highlighted.
     * 
     * @return true if this piece is highlighted
     */
    public boolean isHighlighted() {
        return highlighted;
    }

    /**
     * Sets 3D vertical level of the board where this piece is located.
     * 
     * @param verticalLevel vertical-3D level of the board 
     *                      where this piece is located
     */
    public void setVerticalLevel(int verticalLevel) {
        this.verticalLevel = verticalLevel;
    }

    /**
     * Returns 3D vertical level of the board where this piece is located.
     * @return 3D vertical level of the board where this piece is located
     */
    public int getVerticalLevel() {
        return verticalLevel;
    }

    public static boolean isPiece(Object obj) {
        return obj.getClass().equals(Piece.class)
                || obj.getClass().equals(Pawn.class)
                || obj.getClass().equals(Rook.class)
                || obj.getClass().equals(Knight.class)
                || obj.getClass().equals(Bishop.class)
                || obj.getClass().equals(Queen.class)
                || obj.getClass().equals(King.class);
    }
}
