package main.SaveLoad;

import main.pieces.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Saves current game's turn and pieces for future usage.
 * @author Amy
 */
public class Save implements Serializable {
    
    /** Black player's pieces. */
    private ArrayList<PieceSaver> blackSavedPieces;

    /** White player's pieces. */
    private ArrayList<PieceSaver> whiteSavedPieces;

    /** True if white player's turn is saved. */
    private boolean whiteTurn;

    /**
     * Constructor of this Save.
     */
    Save() {
        blackSavedPieces = new ArrayList<>();
        whiteSavedPieces = new ArrayList<>();
    }

    void saveTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    boolean loadTurn() {
        return whiteTurn;
    }

    void savePieces(ArrayList<Piece> pieces, boolean white) {
        ArrayList<PieceSaver> readyToSave = new ArrayList<>();

        for (Piece p : pieces) {
            int col = p.getColumnIndex();
            int row = p.getRowIndex();
            char type = p.getType();
            boolean firstMove = p.isFirstMove();

            PieceSaver ps = new PieceSaver(white, type, col, row, firstMove);

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
     * @param whitePlayer true if desired pieces belongs to the white player. 
     * @return Saved pieces for the given player.
     */
    public ArrayList<Piece> loadPieces(boolean whitePlayer) {
        ArrayList<Piece> loadedPieces = new ArrayList<>();

        ArrayList<PieceSaver> pieceSaving = whitePlayer ? whiteSavedPieces : blackSavedPieces;

        for (PieceSaver ps : pieceSaving) {
            boolean white = ps.isWhite();
            char type = ps.getType();
            int columnIndex = ps.getColumnIndex();
            int rowIndex = ps.getRowIndex();
            boolean firstMove = ps.isFirstMove();

            switch (type) {
                case '\u265F': // Pawn
                    loadedPieces.add(
                             new Pawn(white, columnIndex, rowIndex, firstMove, 1));
                    break;
                case '\u265E': // Knight
                    loadedPieces.add(new Knight(white, columnIndex, rowIndex, 1));
                    break;
                case '\u265C': // Rook
                    loadedPieces.add(
                             new Rook(white, columnIndex, rowIndex, firstMove, 1));
                    break;
                case '\u265D': // Bishop
                    loadedPieces.add(new Bishop(white, columnIndex, rowIndex, 1));
                    break;
                case '\u265B': // Queen
                    loadedPieces.add(new Queen(white, columnIndex, rowIndex, 1));
                    break;
                case '\u265A': // King
                    loadedPieces.add(
                             new King(white, columnIndex, rowIndex, firstMove, 1));
                    break;
            }
        }

        return loadedPieces;
    }
}
