package main;

import main.pieces.*;

import java.util.ArrayList;

public class Player {

    private ArrayList<Piece> pieces;

    boolean white;

    Player(boolean white) {
        this.white = white;
        pieces = new ArrayList<>();
        initializePieces();
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    private void initializePieces() {
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(white));
        }

        pieces.add(new Rook(white));
        pieces.add(new Knight(white));
        pieces.add(new Bishop(white));
        pieces.add(new Queen(white));
        pieces.add(new King(white));
        pieces.add(new Bishop(white));
        pieces.add(new Knight(white));
        pieces.add(new Rook(white));
    }
}
