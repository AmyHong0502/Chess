package main.board;

import javafx.scene.Node;
import javafx.scene.layout.*;
import main.Player;
import main.console.ColourTheme;
import main.pieces.*;

import java.util.ArrayList;

public class Board extends GridPane {

    /**
     * Number of cells in a row/cell.
     */
    public static final int NUMBER_OF_CELLS = 8;

    private final int zLevel;

    private int tileLength;

    /**
     * Black player.
     */
    private Player blackPlayer;

    /**
     * White player.
     */
    private Player whitePlayer;

    /**
     * ColourTheme object for colouring.
     */
    private ColourTheme colourTheme;

    public Board(Player blackPlayer, Player whitePlayer, ColourTheme colourTheme, final int zLevel) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;

        this.colourTheme = colourTheme;
        this.zLevel = zLevel;

        putTiles();
    }

    public void putTiles() {
        for (int row = 0; row < NUMBER_OF_CELLS; row++) {
            for (int column = 0; column < NUMBER_OF_CELLS; column++) {
                boolean white = (row + column) % 2 == 0;

                tileLength = 60;

                Tile tile = new Tile(zLevel, tileLength, white);
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

    Piece findPieceByIndex(final int columnIndex, final int rowIndex) {
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

    /**
     * Refreshes the display of pieces on this board.
     */
    public void refreshPieces() {
        getChildren().removeAll(blackPlayer.getPieces());
        getChildren().removeAll(whitePlayer.getPieces());

        drawPieces();
    }

}
