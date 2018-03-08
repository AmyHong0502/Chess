package main.board;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import main.Player;
import main.pieces.Pawn;
import main.pieces.Piece;

import java.util.ArrayList;
import java.util.Iterator;

public class Board extends GridPane {

    private Piece clickedPiece;

    public static int NUMBER_OF_CELLS = 8;

    private int tileLength;

    private Player blackPlayer;

    private Player whitePlayer;

    public Board(Player blackPlayer, Player whitePlayer) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        drawBoard(blackPlayer, whitePlayer);
    }

    private void putTiles() {
        for (int row = 0; row < NUMBER_OF_CELLS; row++) {
            for (int column = 0; column < NUMBER_OF_CELLS; column++) {
                boolean white = (row + column) % 2 == 0;

                tileLength = 60;

                Tile tile = new Tile(column, row, tileLength, false, white, 1);
                tile.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> moveClickedPiece(clickedPiece, GridPane.getColumnIndex(tile), GridPane.getRowIndex(tile)));

                add(tile, column, row);
            }
        }
    }

    public void drawBoard(Player player1, Player player2) {
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

    private void paintTileDefault() {
        for (int row = 0; row < NUMBER_OF_CELLS; row++) {
            for (int column = 0; column < NUMBER_OF_CELLS; column++) {
                getChildren().forEach(
                        (Node c) -> {
                            if (c.getClass().equals(Tile.class)) {
                                ((Tile) c).setColor(((Tile) c).white, 1);
                            }
                        }
                );
            }
        }
    }

    private boolean isAllowedToMove(final int[][] movable, final int destColumnIndex, final int destRowIndex) {
        for (int[] cell : movable) {
            if (cell[0] == destColumnIndex && cell[1] == destRowIndex) {
                return true;
            }
        }
        return false;
    }

    private void moveClickedPiece(final Piece piece, final int destColumnIndex, final int destRowIndex) {
        if (piece == null) {
            System.out.println("Piece is null");
        }
        boolean turn = piece.isWhite() ? whitePlayer.isMyTurn() : blackPlayer.isMyTurn();

        if (turn) {
            if (piece.isClicked()) {
                final int[][] movable = piece.movable();

                if (isAllowedToMove(movable, destColumnIndex, destRowIndex)) {
                    if (findPieceByIndex(destColumnIndex, destRowIndex) != null) {
                        capturePiece(piece, destColumnIndex, destRowIndex);
                        return;
                    } else if (isClearPath(piece, destColumnIndex, destRowIndex)) {
                        clickedPiece.setColumnIndex(destColumnIndex);
                        clickedPiece.setRowIndex(destRowIndex);
                        setColumnIndex(clickedPiece, clickedPiece.getColumnIndex());
                        setRowIndex(clickedPiece, clickedPiece.getRowIndex());
                        piece.setClicked(false);
                        piece.highlightClickedPiece();
                        if (clickedPiece.getClass().equals(Pawn.class)) {
                            ((Pawn) clickedPiece).setFirstMove();
                        }
                        switchTurn();
                    } else {
                        System.out.println("Not Clear Path");
                        piece.setClicked(false);
                        piece.highlightClickedPiece();
                    }
                }
            } else {
                relocatePiece(piece, destColumnIndex, destRowIndex);
            }

            System.out.println("Point 08");
            piece.setClicked(false);
        }

        System.out.println("\n\n");
    }

    private void relocatePiece(Piece piece, final int destColumnIndex, final int destRowIndex) {
        piece.setColumnIndex(destColumnIndex);
        piece.setRowIndex(destRowIndex);
        clickedPiece = piece;
    }

    public boolean isClearPath(final Piece piece, final int destColumnIndex, final int destRowIndex) {
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

    private void switchTurn() {
        if (whitePlayer.isMyTurn()) {
            whitePlayer.finishMyTurn();
            blackPlayer.startMyTurn();
        } else {
            blackPlayer.finishMyTurn();
            whitePlayer.startMyTurn();
        }
    }

    private void capturePiece(final Piece predator, final int columnIndex, final int rowIndex) {
        Piece prey = findPieceByIndex(columnIndex, rowIndex);
        ArrayList<Piece> pieces;

        pieces = prey.isWhite() ? whitePlayer.getPieces() : blackPlayer.getPieces();

        Iterator itr = pieces.iterator();
        while (itr.hasNext()) {
            Piece piece = (Piece) itr.next();
            if (piece.equals(prey)) {
                itr.remove();
            }
        }

        refreshPieces();
        predator.setClicked(false);
        moveClickedPiece(predator, columnIndex, rowIndex);
    }

    private void highlightHovered(final Piece piece) {
        boolean turn = piece.isWhite() ? whitePlayer.isMyTurn() : blackPlayer.isMyTurn();

        if (turn) {
            int[][] movable = piece.movable();

            for (int[] location : movable) {
                int col = location[0];
                int row = location[1];

                Tile tile = findTileByIndex(col, row);
                tile.setMovableHighlight();
            }
        }
    }

    private void setClickedPiece(Piece piece) {
        clickedPiece = piece;
    }

    private void refreshPieces() {
        getChildren().clear();
        drawBoard(blackPlayer, whitePlayer);
    }

    private void populatePieces(final Player blackPlayer, final Player whitePlayer) {
        ArrayList<Piece> blackPieces = blackPlayer.getPieces();
        ArrayList<Piece> whitePieces = whitePlayer.getPieces();

        for (Piece piece : blackPieces) {
            add(piece, piece.getColumnIndex(), piece.getRowIndex());
        }

        for (Piece piece : whitePieces) {
            add(piece, piece.getColumnIndex(), piece.getRowIndex());
        }

        initializeHoverHighlighter(blackPlayer.getPieces(), whitePlayer.getPieces());
    }

    private void initializeHoverHighlighter(final ArrayList<Piece> blackPieces, final ArrayList<Piece> whitePieces) {
        for (Piece p : blackPieces) {
            p.addEventFilter(MouseEvent.MOUSE_ENTERED,
                    event -> highlightHovered(p));
        }

        for (Piece p : blackPieces) {
            p.addEventFilter(MouseEvent.MOUSE_EXITED,
                    event -> paintTileDefault());
        }

        for (Piece p : whitePieces) {
            p.addEventFilter(MouseEvent.MOUSE_ENTERED,
                    event -> highlightHovered(p));
        }

        for (Piece p : whitePieces) {
            p.addEventFilter(MouseEvent.MOUSE_EXITED,
                    event -> paintTileDefault());
        }

        for (Piece p : blackPieces) {
            p.addEventFilter(MouseEvent.MOUSE_CLICKED,
                    event -> setClickedPiece(p));
        }

        for (Piece p : whitePieces) {
            p.addEventFilter(MouseEvent.MOUSE_CLICKED,
                    event -> setClickedPiece(p));
        }
    }

}
