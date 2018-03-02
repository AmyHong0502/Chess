package main.SaveLoad;

import main.pieces.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {
    private ArrayList<PiecesSaver> blackSavedPieces;

    private ArrayList<PiecesSaver> whiteSavedPieces;

    Save() {
        blackSavedPieces = new ArrayList<>();
        whiteSavedPieces = new ArrayList<>();
    }

    void savePieces(ArrayList<Piece> pieces, boolean white) {
        ArrayList<PiecesSaver> readyToSave = new ArrayList<>();

        for (Piece p : pieces) {
            int col = p.getColumnIndex();
            int row = p.getRowIndex();
            char type = p.getType();

            PiecesSaver ps = new PiecesSaver(white, type, col, row);

            readyToSave.add(ps);
            System.out.println(ps.toString());
        }

        if (white) {
            whiteSavedPieces = readyToSave;
        } else {
            blackSavedPieces = readyToSave;
        }
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
                    break;
                case '\u265E': // Knight
                    loadedPieces.add(new Knight(white, columnIndex, rowIndex));
                    break;
                case '\u265C': // Rook
                    loadedPieces.add(new Rook(white, columnIndex, rowIndex));
                    break;
                case '\u265D': // Bishop
                    loadedPieces.add(new Bishop(white, columnIndex, rowIndex));
                    break;
                case '\u265B': // Queen
                    loadedPieces.add(new Queen(white, columnIndex, rowIndex));
                    break;
                case '\u265A': // King
                    loadedPieces.add(new King(white, columnIndex, rowIndex));
                    break;
            }
        }

        return loadedPieces;
    }
}
