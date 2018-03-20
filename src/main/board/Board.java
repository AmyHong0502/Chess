package main.board;

import javafx.scene.Node;
import javafx.scene.layout.*;
import main.Player;
import main.console.ColourTheme;
import main.pieces.*;

import java.util.ArrayList;

public class Board extends GridPane {

    /** Number of cells in a row/cell. */
    public static final int NUMBER_OF_CELLS = 8;

    /** Vertical-3D level of this board. */
    private final int verticalLevel;

    /** Length of tiles on this board. */
    private int tileLength;

    /** Player who has black pieces. */
    private Player blackPlayer;

    /** Player who has white pieces. */
    private Player whitePlayer;

    /** ColourTheme object for colouring. */
    private ColourTheme colourTheme;

    /**
     * Constructor of this board.
     *
     * @param blackPlayer   Player who has black pieces
     * @param whitePlayer   Player who has white pieces
     * @param colourTheme   ColourTheme object for colouring
     * @param verticalLevel Vertical-3D level of this board
     */
    public Board(Player blackPlayer, Player whitePlayer, ColourTheme colourTheme, final int verticalLevel) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;

        this.colourTheme = colourTheme;
        this.verticalLevel = verticalLevel;

        putTiles();
    }

    public void putTiles() {
        for (int row = 0; row < NUMBER_OF_CELLS; row++) {
            for (int column = 0; column < NUMBER_OF_CELLS; column++) {
                boolean white = (row + column) % 2 == 0;

                tileLength = 60;

                Tile tile = new Tile(verticalLevel, tileLength, white);
                add(tile, column, row);
            }
        }
    }

    void drawPieces() {
        ArrayList<Piece> blackPieces = blackPlayer.getPieces(verticalLevel);
        ArrayList<Piece> whitePieces = whitePlayer.getPieces(verticalLevel);

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
                colourTheme.highlightTile(tile, verticalLevel);
            }
        }
    }


    /**
     * Returns vertical level of this board: 0 for top, 1 for middle, and 2 for bottom board.
     *
     * @return vertical level of this board
     */
    public int getVerticalLevel() {
        return verticalLevel;
    }

    /**
     * Refreshes the display of pieces on this board.
     */
    public void refreshPieces() {
        removePiecesFromBoard();
        drawPieces();
    }

    /**
     * Removes all pieces on this board. The pieces still belong to the player and contain this board's vertical level information but this program does not draw the pieces on this board anymore.
     */
    public void removePiecesFromBoard() {
        ArrayList<Piece> piecesOnBoard = new ArrayList<>();

        for (Node node: getChildren()) {
            if (Piece.isPiece(node)) {
                piecesOnBoard.add((Piece) node);
            }
        }

        getChildren().removeAll(piecesOnBoard);
    }

}
