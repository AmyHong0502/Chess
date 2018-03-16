package main;

import javafx.scene.Node;
import main.pieces.*;

import java.util.ArrayList;

public class Player extends Node {

    /** This player's pieces. */
    private ArrayList<Piece> pieces;

    /** True if this player has white pieces. */
    private boolean white;

    /** True if it is this player's turn. */
    private boolean myTurn;

    /**
     * Constructor of this Player.
     * 
     * @param white true if this player has white pieces
     */
    Player(boolean white) {
        super();
        this.white = white;
        pieces = new ArrayList<>();
        initializePieces();
        myTurn = white;
    }

    /**
     * Starts this player's turn.
     */
    public void startMyTurn() {
        myTurn = true;
    }

    /**
     * Ends this player's turn.
     */
    public void finishMyTurn() {
        myTurn = false;
    }

    /**
     * Returns true if it is this player's turn.
     * 
     * @return true if it is this player's turn
     */
    public boolean isMyTurn() { 
        return myTurn; 
    }

    /**
     * Sets this player's pieces.
     * 
     * @param pieces pieces for this player
     */
    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    /**
     * Returns true if this player has white pieces.
     * 
     * @return true if this player has white pieces
     */
    public boolean isWhite() {
        return white;
    }

    /**
     * Returns this player's pieces.
     * 
     * @return this player's pieces
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    /**
     * Returns this player's pieces on the given vertical level.
     * 
     * @param verticalLevel vertical-3D level of the board to get pieces
     * @return              pieces on the given vertical level
     */
    public ArrayList<Piece> getPieces(final int verticalLevel) {
        ArrayList<Piece> piecesOnVLevel = new ArrayList<>();
        for (Piece p: pieces) {
            if (p.getVerticalLevel() == verticalLevel) {
                piecesOnVLevel.add(p);
            }
        }

        return piecesOnVLevel;
    }

    private void initializePieces() {
        if (white) {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(white, i, 6, 1, true));
            }

            pieces.add(new Rook(white, 0, 7, 1, true));
            pieces.add(new Knight(white, 1, 7, 1));
            pieces.add(new Bishop(white, 2, 7, 1));
            pieces.add(new Queen(white, 3, 7, 1));
            pieces.add(new King(white, 4, 7, 1, true));
            pieces.add(new Bishop(white, 5, 7, 1));
            pieces.add(new Knight(white, 6, 7, 1));
            pieces.add(new Rook(white, 7, 7, 1, true));
        } else {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(white, i, 1, 1, true));
            }

            pieces.add(new Rook(white, 0, 0, 1, true));
            pieces.add(new Knight(white, 1, 0, 1));
            pieces.add(new Bishop(white, 2, 0, 1));
            pieces.add(new Queen(white, 3, 0, 1));
            pieces.add(new King(white, 4, 0, 1, true));
            pieces.add(new Bishop(white, 5, 0, 1));
            pieces.add(new Knight(white, 6, 0, 1));
            pieces.add(new Rook(white, 7, 0, 1, true));
        }

        for (Piece p: pieces) {
            p.initialSetup();
        }
    }
}
