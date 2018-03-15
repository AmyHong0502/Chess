package main.board;

import javafx.scene.Node;
import javafx.scene.layout.*;
import main.Player;
import main.console.ColourTheme;
import main.pieces.Piece;

import java.util.ArrayList;

public class Board extends GridPane {

    private final int zLevel;

    public static int NUMBER_OF_CELLS = 8;

    private int tileLength;

    private Player blackPlayer;

    private Player whitePlayer;

    private ColourTheme colourTheme;

    public Board(Player blackPlayer, Player whitePlayer, ColourTheme colourTheme, final int zLevel) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;

        putTiles();

        this.colourTheme = colourTheme;
        this.zLevel = zLevel;
    }

    public void putTiles() {
        for (int row = 0; row < NUMBER_OF_CELLS; row++) {
            for (int column = 0; column < NUMBER_OF_CELLS; column++) {
                boolean white = (row + column) % 2 == 0;

                tileLength = 60;

                Tile tile = new Tile(zLevel, tileLength, false, white);
                add(tile, column, row);
            }
        }
    }

    void drawPieces() {
        ArrayList<Piece> blackPieces = blackPlayer.getPieces(zLevel);
        ArrayList<Piece> whitePieces = whitePlayer.getPieces(zLevel);

        for (Piece piece : blackPieces) {
            add(piece, piece.getColumnIndex(), piece.getRowIndex());
        }

        for (Piece piece : whitePieces) {
            add(piece, piece.getColumnIndex(), piece.getRowIndex());
        }
    }

    private Tile findTileByIndex(final int columnIndex, final int rowIndex) {
        for (Node node : getChildren()) {
            if (node.getClass().equals(Tile.class)) {
                if (GridPane.getColumnIndex(node) == columnIndex && GridPane.getRowIndex(node) == rowIndex) {
                    return (Tile) node;
                }
            }
        }

        return null;
    }

    private Piece findPieceByIndex(final int columnIndex, final int rowIndex) {
        ArrayList<Piece> blackPieces = blackPlayer.getPieces();
        ArrayList<Piece> whitePieces = whitePlayer.getPieces();

        for (Piece p : blackPieces) {
            if (p.getColumnIndex() == columnIndex && p.getRowIndex() == rowIndex) {
                return p;
            }
        }
        for (Piece p : whitePieces) {
            if (p.getColumnIndex() == columnIndex && p.getRowIndex() == rowIndex) {
                return p;
            }
        }

        return null;
    }

    public boolean isClearPath(final Piece piece, final int destColumnIndex, 
                                                       final int destRowIndex) {
        int[][] possibilities = piece.searchPath(destColumnIndex, destRowIndex);

        for (int[] cell : possibilities) {
            int column = cell[0];
            int row = cell[1];

            if (findPieceByIndex(column, row) != null) {
                return false;
            }
        }

        return true;
    }

//    private void capturePiece(final int columnIndex, final int rowIndex) {
//        if (clickedPiece == null) {
//            return;
//        }
//
//        Piece prey = findPieceByIndex(columnIndex, rowIndex);
//        ArrayList<Piece> pieces;
//
//        pieces = prey.isWhite() ? whitePlayer.getPieces()
//                                                      : blackPlayer.getPieces();
//
//        Iterator itr = pieces.iterator();
//        while (itr.hasNext()) {
//            Piece piece = (Piece) itr.next();
//            if (piece.equals(prey)) {
//                itr.remove();
//            }
//        }
//
//        refreshPieces();
//        colourTheme.paintByTheme(this, zLevel);
//        moveClickedPiece(columnIndex, rowIndex);
//        relocatePiece(columnIndex, rowIndex);
//    }

    public void highlightTiles(final Piece piece) {
        boolean turn = piece.isWhite() ? whitePlayer.isMyTurn() : blackPlayer.isMyTurn();

        if (turn) {
            int[][] movable = piece.movable();

            for (int[] location : movable) {
                int col = location[0];
                int row = location[1];

                Tile tile = findTileByIndex(col, row);
                colourTheme.highlightTile(tile, zLevel);
            }
        }
    }

    void refreshPieces() {
        getChildren().clear();
        drawPieces();
    }

}
