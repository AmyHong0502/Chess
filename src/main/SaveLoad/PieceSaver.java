package main.SaveLoad;

import java.io.Serializable;

/**
 * Saves one piece per each PieceSaver object.
 * @author Amy
 */
public class PieceSaver implements Serializable {
    
    /** Column index of this piece to save. */
    private final int columnIndex;
    
    /** Row index of this piece to save. */
    private final int rowIndex;
    
    /**
     * Type of this piece to save: 
     * Pawn, Knight, Rook, Bishop, Queen, or King. 
     */
    private final char type;
    
    /** True if this piece to save is white. */
    private final boolean white;
    
    /** True if this piece to save has never moved. */
    private final boolean firstMove;
    
    /**
     * Constructor of this PieceSaver.
     * @param white       True if this piece to save is white
     * @param type        Type of this piece to save: 
     *                    Pawn, Knight, Rook, Bishop, Queen, or King
     * @param columnIndex Column index of this piece to save
     * @param rowIndex    Row index of this piece to save
     * @param firstMove   True if this piece to save has never moved
     */ 
    PieceSaver(boolean white, char type,
                int columnIndex, int rowIndex, boolean firstMove) {
        this.white = white;
        this.type = type;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.firstMove = firstMove;
    }

    /**
     * Returns type of this saved piece.
     * @return type of this saved piece
     */
    public char getType() {
        return type;
    }
    
    /**
     * Returns true if this saved piece is white. 
     * @return true if this saved piece is white
     */
    public boolean isWhite() {
        return white;
    }

    /**
     * Returns column's index of this saved piece.
     * @return column's index of this saved piece
     */
    public int getColumnIndex() {
        return columnIndex;
    }

    /**
     * Returns row's index of this saved piece.
     * @return row's index of this saved piece
     */
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Returns true if this saved piece never moved from the beginning of this 
     * saved game.
     * @return true if this saved piece never moved 
     *              from the beginning of this saved game
     */
    public boolean isFirstMove() {
        return firstMove; 
    }
}
