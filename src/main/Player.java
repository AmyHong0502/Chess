package main;

import main.pieces.Pawn;
import main.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Piece> pieces;

    boolean white;

    Player(boolean white) {
        this.white = white;
        pieces = new ArrayList<>();
        addPieces();
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    private void addPieces() {
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(i, 6, !white));
        }
    }
}
