package main;

import javafx.scene.Node;
import main.console.ColourTheme;
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

    private void initializePieces() {
        if (white) {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(white, i, 6, true));
            }

            pieces.add(new Rook(white, 0, 7, true));
            pieces.add(new Knight(white, 1, 7));
            pieces.add(new Bishop(white, 2, 7));
            pieces.add(new Queen(white, 3, 7));
            pieces.add(new King(white, 4, 7, true));
            pieces.add(new Bishop(white, 5, 7));
            pieces.add(new Knight(white, 6, 7));
            pieces.add(new Rook(white, 7, 7, true));
        } else {
            for (int i = 0; i < 8; i++) {
                pieces.add(new Pawn(white, i, 1, true));
            }

            pieces.add(new Rook(white, 0, 0, true));
            pieces.add(new Knight(white, 1, 0));
            pieces.add(new Bishop(white, 2, 0));
            pieces.add(new Queen(white, 3, 0));
            pieces.add(new King(white, 4, 0, true));
            pieces.add(new Bishop(white, 5, 0));
            pieces.add(new Knight(white, 6, 0));
            pieces.add(new Rook(white, 7, 0, true));
        }

        for (Piece p: pieces) {
            p.initialSetup();
        }
    }
}
