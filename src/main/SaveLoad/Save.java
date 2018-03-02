package main.SaveLoad;

import main.pieces.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {

    private ArrayList<Piece> blackPieces;

    private ArrayList<Piece> whitePieces;

    private ArrayList<PiecesSaver> blackSavedPieces;

    private ArrayList<PiecesSaver> whiteSavedPieces;

    Save() {
        blackPieces = new ArrayList<>();
        whitePieces = new ArrayList<>();
        blackSavedPieces = new ArrayList<>();
        whiteSavedPieces = new ArrayList<>();
        System.out.println("Constructor");
    }

    public void setBlackSavedPieces(ArrayList<PiecesSaver> blackSavedPieces) {

    }

    public void setWhiteSavedPieces(ArrayList<PiecesSaver> whiteSavedPieces) {
    }

    void savePieces() {


        for (Piece p : whitePieces) {
            int col = p.getColumnIndex();
            int row = p.getRowIndex();
            boolean white = true;
            char type = p.getType();

            whiteSavedPieces.add(new PiecesSaver(white, type, col, row));
        }
        System.out.println("savedPieces");
    }

    public ArrayList<Piece> loadPieces(boolean whitePlayer) {
        ArrayList<Piece> loadedPieces = new ArrayList<>();

        ArrayList<PiecesSaver> pieceSaving = whitePlayer ? whiteSavedPieces : blackSavedPieces;

        for (PiecesSaver ps : pieceSaving) {
            boolean white = ps.isWhite();
            char type = ps.getType();
            int columnIndex = ps.getColumnIndex();
            int rowIndex = ps.getRowIndex();

            switch (type) {
                case '\u265F': // Pawn
                    loadedPieces.add(new Pawn(white, columnIndex, rowIndex));
                    System.out.println("Pawn");
                    break;
                case '\u265E': // Knight
                    loadedPieces.add(new Knight(white, columnIndex, rowIndex));
                    System.out.println("Knight");
                    break;
                case '\u265C': // Rook
                    loadedPieces.add(new Rook(white, columnIndex, rowIndex));
                    System.out.println("Rook");
                    break;
                case '\u265D': // Bishop
                    loadedPieces.add(new Bishop(white, columnIndex, rowIndex));
                    System.out.println("Bishop");
                    break;
                case '\u265B': // Queen
                    loadedPieces.add(new Queen(white, columnIndex, rowIndex));
                    System.out.println("Queen");
                    break;
                case '\u265A': // King
                    loadedPieces.add(new King(white, columnIndex, rowIndex));
                    System.out.println("King");
                    break;
            }
        }

        System.out.println("loadPieces: " + whitePlayer);
        return loadedPieces;
    }
}
