package main.board;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import main.Player;
import main.pieces.Pawn;
import main.pieces.Piece;

import java.io.Serializable;
import java.util.ArrayList;

public class Board extends GridPane implements Serializable {

    private Piece clickedPiece;

    public static int NUMBER_OF_CELLS = 8;

    private int tileLength;

    private boolean clickedToMove;

    public Board(Player player1, Player player2) {
        drawBoard(player1, player2);
        clickedToMove = false;
    }

    private void putTiles() {
        for (int row = 0; row < NUMBER_OF_CELLS; row++) {
            for (int column = 0; column < NUMBER_OF_CELLS; column++) {
                boolean white = (row + column) % 2 == 0;

                tileLength = 60;

                Tile tile = new Tile(column, row, tileLength, false, white);
                tile.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> moveClickedPiece(clickedPiece, GridPane.getColumnIndex(tile), GridPane.getRowIndex(tile)));

                add(tile, column, row);
            }
        }
    }

    public void drawBoard(Player player1, Player player2) {
        getChildren().clear();
        putTiles();
        populatePieces(player1, player2);
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

    private void paintTileDefault() {
        for (int row = 0; row < NUMBER_OF_CELLS; row++) {
            for (int column = 0; column < NUMBER_OF_CELLS; column++) {
                getChildren().forEach(
                        (Node c) -> {
                            if (c.getClass().equals(Tile.class)) {
                                ((Tile) c).setColor(((Tile) c).white);
                            }
                        }
                );
            }
        }
    }

    private void moveClickedPiece(final Piece piece, final int columnIndex, final int rowIndex) {
        if (clickedToMove) {
            final int[][] movable = piece.movable();
            boolean allowedToMove = false;

            for (int[] location : movable) {
                if (location[0] == columnIndex && location[1] == rowIndex) {
                    allowedToMove = true;
                }
            }

            if (allowedToMove) {
                clickedPiece.setColumnIndex(columnIndex);
                clickedPiece.setRowIndex(rowIndex);
                setColumnIndex(clickedPiece, clickedPiece.getColumnIndex());
                setRowIndex(clickedPiece, clickedPiece.getRowIndex());
                piece.setClicked(false);
                piece.highlightClickedPiece();
                if (clickedPiece.getClass().equals(Pawn.class)) {
                    ((Pawn) clickedPiece).setFirstMove();
                }
            }
        } else {
            piece.setColumnIndex(columnIndex);
            piece.setRowIndex(rowIndex);
            clickedPiece = piece;
        }
        clickedToMove = !clickedToMove;
    }

    private void highlightHovered(final Piece piece) {
        int[][] movable = piece.movable();

        for (int[] location : movable) {
            int col = location[0];
            int row = location[1];

            Tile tile = findTileByIndex(col, row);
            tile.setMovableHighlight();
        }
    }

    private void populatePieces(final Player player1, final Player player2) {
        ArrayList<Piece> pieces1 = player1.getPieces();
        ArrayList<Piece> pieces2 = player2.getPieces();

        for (Piece piece : pieces1) {
            add(piece, piece.getColumnIndex(), piece.getRowIndex());
        }

        for (Piece piece : pieces2) {
            add(piece, piece.getColumnIndex(), piece.getRowIndex());
        }

        initializeHoverHighlighter(player1.getPieces(), player2.getPieces());
    }

    private void initializeHoverHighlighter(final ArrayList<Piece> pieces1, final ArrayList<Piece> pieces2) {
        for (Piece p : pieces1) {
            p.addEventFilter(MouseEvent.MOUSE_ENTERED,
                    event -> highlightHovered(p));
        }

        for (Piece p : pieces1) {
            p.addEventFilter(MouseEvent.MOUSE_EXITED,
                    event -> paintTileDefault());
        }

        for (Piece p : pieces2) {
            p.addEventFilter(MouseEvent.MOUSE_ENTERED,
                    event -> highlightHovered(p));
        }

        for (Piece p : pieces2) {
            p.addEventFilter(MouseEvent.MOUSE_EXITED,
                    event -> paintTileDefault());
        }

        for (Piece p : pieces1) {
            p.addEventFilter(MouseEvent.MOUSE_CLICKED,
                    event -> moveClickedPiece(p, GridPane.getColumnIndex(p), GridPane.getRowIndex(p)));
        }

        for (Piece p : pieces2) {
            p.addEventFilter(MouseEvent.MOUSE_CLICKED,
                    event -> moveClickedPiece(p, GridPane.getColumnIndex(p), GridPane.getRowIndex(p)));
        }
    }

}
