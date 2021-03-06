package me.amyhong.saveLoad;

import me.amyhong.pieces.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Saves current game's turn and pieces for future usage.
 * @author Amy
 */
public class SaveLoad implements Serializable {
    
    /** Black player's pieces. */
    private ArrayList<PieceSaver> blackSavedPieces;

    /** White player's pieces. */
    private ArrayList<PieceSaver> whiteSavedPieces;

    /** True if white player's turn is saved. */
    private boolean whiteTurn;

    /**
     * Constructor of this SaveLoad.
     */
    public SaveLoad() {
        blackSavedPieces = new ArrayList<>();
        whiteSavedPieces = new ArrayList<>();
    }

    /**
     * Saves who's turn it is for the moment the user saves the game.
     * 
     * @param whiteTurn true if it is the white user's turn
     */
    public void saveTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }
    
    /**
     * Returns true if the saved game was the white player's turn.
     * 
     * @return true if the saved game was the white player's turn
     */
    public boolean loadTurn() {
        return whiteTurn;
    }

    /**
     * Saves a user's pieces for the current game.
     * 
     * @param pieces pieces of a user. A user can be black or white.
     * @param white  true if this pieces to save belong to the white user. 
     */
    public void savePieces(ArrayList<Piece> pieces, boolean white) {
        ArrayList<PieceSaver> readyToSave = new ArrayList<>();

        for (Piece p : pieces) {
            int col = p.getColumnIndex();
            int row = p.getRowIndex();
            char type = p.getType();
            boolean firstMove = p.isNeverMoved();
            int verticalLevel = p.getVerticalLevel();

            PieceSaver ps = new PieceSaver(white, type, firstMove, col, row, verticalLevel);

            readyToSave.add(ps);
        }

        if (white) {
            whiteSavedPieces = readyToSave;
        } else {
            blackSavedPieces = readyToSave;
        }
    }

    /**
     * Loads pieces to continue the saved game.
     * 
     * @param whitePlayer true if desired pieces belongs to the white player.
     * @return Saved pieces for the given player.
     */
    public ArrayList<Piece> loadPieces(boolean whitePlayer) {
        ArrayList<Piece> loadedPieces = new ArrayList<>();

        ArrayList<PieceSaver> pieceSaving = whitePlayer 
                                          ? whiteSavedPieces : blackSavedPieces;

        for (PieceSaver ps : pieceSaving) {
            boolean white = ps.isWhite();
            char type = ps.getType();
            boolean neverMoved = ps.isNeverMoved();
            int columnIndex = ps.getColumnIndex();
            int rowIndex = ps.getRowIndex();
            int verticalLevel = ps.getVerticalLevel();

            switch (type) {
                case '\u265F': // Pawn
                    loadedPieces.add(
                          new Pawn(white, columnIndex, rowIndex, verticalLevel, neverMoved));
                    break;
                case '\u265E': // Knight
                    loadedPieces.add(new Knight(white, columnIndex, rowIndex, verticalLevel));
                    break;
                case '\u265C': // Rook
                    loadedPieces.add(
                          new Rook(white, columnIndex, rowIndex, verticalLevel, neverMoved));
                    break;
                case '\u265D': // Bishop
                    loadedPieces.add(new Bishop(white, columnIndex, rowIndex, verticalLevel));
                    break;
                case '\u265B': // Queen
                    loadedPieces.add(new Queen(white, columnIndex, rowIndex, verticalLevel));
                    break;
                case '\u265A': // King
                    loadedPieces.add(
                          new King(white, columnIndex, rowIndex, verticalLevel, neverMoved));
                    break;
            }
        }

        for (Piece piece: loadedPieces) {
            piece.initialSetup();
        }

        return loadedPieces;
    }
}
