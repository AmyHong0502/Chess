package main;

import javafx.scene.Node;
import main.pieces.*;

import java.util.ArrayList;

public class Player extends Node {

    private ArrayList<Piece> pieces;

    private boolean white;

    private boolean clickedPiece;

    private boolean myTurn;

    Player(boolean white) {
        super();
        this.white = white;
        pieces = new ArrayList<>();
        initializePieces();
        clickedPiece = false;
        myTurn = white;
    }

    public void startMyTurn() {
        myTurn = true;
    }

    public void finishMyTurn() {
        myTurn = false;
    }

    public boolean isMyTurn() { return myTurn; }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean isWhite() {
        return white;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public ArrayList<Piece> getPieces(final int zlevel) {
        ArrayList<Piece> piecesOnZlevel = new ArrayList<>();
        for (Piece p: pieces) {
            if (p.getzLevel() == zlevel) {
                piecesOnZlevel.add(p);
            }
        }

        return piecesOnZlevel;
    }

    private void initializePieces() {
        if (white) {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(white, i, 6, 1,true));
            }

            pieces.add(new Rook(white, 0, 7, 1,true));
            pieces.add(new Knight(white, 1, 7, 1));
            pieces.add(new Bishop(white, 2, 7, 1));
            pieces.add(new Queen(white, 3, 7, 1));
            pieces.add(new King(white, 4, 7, 1, true));
            pieces.add(new Bishop(white, 5, 7, 1));
            pieces.add(new Knight(white, 6, 7, 1));
            pieces.add(new Rook(white, 7, 7, 1, true));
        } else {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(white, i, 1, 1,true));
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
