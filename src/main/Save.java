package main;

import main.pieces.Piece;

import java.util.ArrayList;

public class Save {

    private ArrayList<Piece> player1Pieces;

    private ArrayList<Piece> player2Pieces;

    Save(ArrayList<Piece> player1Pieces, ArrayList<Piece> player2Pieces) {
        this.player1Pieces = player1Pieces;
        this.player2Pieces = player2Pieces;
    }

    public void setPlayer1Pieces(ArrayList<Piece> player1Pieces) {
        this.player1Pieces = player1Pieces;
    }

    public void setPlayer2Pieces(ArrayList<Piece> player2Pieces) {
        this.player2Pieces = player2Pieces;
    }

    public ArrayList<Piece> getPlayer1Pieces() {
        return player1Pieces;
    }

    public ArrayList<Piece> getPlayer2Pieces() {
        return player2Pieces;
    }
}
