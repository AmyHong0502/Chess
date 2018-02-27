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

    Piece clickedPiece;

    public static int NUMBER_OF_CELLS = 8;

    private int tileLength;

    private int boardLength;

    private boolean clickedToMove;

    private int pieceColumnIndex;

    private int pieceRowIndex;

    public Board(Player player1, Player player2) {

        buildBoard();
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);
        initializePieces(player1, player2);
        clickedToMove = false;
    }

    public void buildBoard() {
        for (int row = 0; row < NUMBER_OF_CELLS; row++) {
            for (int column = 0; column < NUMBER_OF_CELLS; column++) {
                fillBackgroundColumn();
                fillBackgroundRow();

                boolean white = (row + column) % 2 == 0;

                tileLength = 60;
                boardLength = tileLength * NUMBER_OF_CELLS;

                Tile tile = new Tile(column, row, tileLength, false, white);
                tile.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                    moveClickedPiece(clickedPiece, GridPane.getColumnIndex(tile), GridPane.getRowIndex(tile));
                });

                add(tile, column, row);
            }
        }
    }

    private void fillBackgroundColumn() {
        ColumnConstraints cc = new ColumnConstraints();

        cc.setPercentWidth(100 / NUMBER_OF_CELLS);

        cc.setHgrow(Priority.ALWAYS);
        cc.setFillWidth(true);
        getColumnConstraints().add(cc);
    }

    private void fillBackgroundRow() {
        RowConstraints rc = new RowConstraints();

        rc.setPercentHeight(100 / NUMBER_OF_CELLS);

        rc.setVgrow(Priority.ALWAYS);
        rc.setFillHeight(true);
        getRowConstraints().add(rc);
    }

    public Tile findTileByIndex(final int columnIndex, final int rowIndex) {
        for (Node node : getChildren()) {
            if (node.getClass().equals(Tile.class)) {
                if (GridPane.getColumnIndex(node) == columnIndex && GridPane.getRowIndex(node) == rowIndex) {
                    return (Tile) node;
                }
            }
        }

        return null;
    }

    public int getBoardLength() {
        return boardLength;
    }

    public void paintDefault() {
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
            int[][] movable = piece.movable(pieceColumnIndex, pieceRowIndex);
            boolean allowedToMove = false;

            for (int i = 0; i < movable.length; i++) {
                if (movable[i][0] == columnIndex && movable[i][1] == rowIndex) {
                    allowedToMove = true;
                }
            }

            if (allowedToMove) {
                setColumnIndex(clickedPiece, columnIndex);
                setRowIndex(clickedPiece, rowIndex);
                if (clickedPiece.getClass().equals(Pawn.class)) {
                    ((Pawn) clickedPiece).setFirstMove();
                }
            }
        } else {
            pieceColumnIndex = columnIndex;
            pieceRowIndex = rowIndex;
            clickedPiece = piece;
        }
        clickedToMove = !clickedToMove;
    }

    private void highlightHovered(final Piece piece, final int columnIndex, final int rowIndex) {
        int[][] movable = piece.movable(columnIndex, rowIndex);

        for (int i = 0; i < movable.length; i++) {
            int col = movable[i][0];
            int row = movable[i][1];

            Tile tile = findTileByIndex(col, row);
            tile.setMovableHighlight();
        }
    }

    private void initializePieces(final Player player1, final Player player2) {
        ArrayList<Piece> pieces1 = player1.getPieces();
        ArrayList<Piece> pieces2 = player2.getPieces();

        int pieceCount = 0;

        for (int row = 1; row >= 0; row--) {
            for (int column = 7; column >= 0; column--) {
                Piece piece = pieces1.get(pieceCount);
                pieceCount++;
                add(piece, column, row);
            }
        }

        pieceCount = 0;

        for (int row = 6; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Piece piece = pieces2.get(pieceCount);
                pieceCount++;
                add(piece, column, row);
            }
        }

        initializeHoverHighlighter(player1.getPieces(), player2.getPieces());
    }

    private void initializeHoverHighlighter(final ArrayList<Piece> pieces1, final ArrayList<Piece> pieces2) {
        for (Piece p : pieces1) {
            p.addEventFilter(MouseEvent.MOUSE_ENTERED,
                    event -> {
                        highlightHovered(p, GridPane.getColumnIndex(p), GridPane.getRowIndex(p));
                    });
        }

        for (Piece p : pieces1) {
            p.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
                paintDefault();
            });
        }

        for (Piece p : pieces2) {
            p.addEventFilter(MouseEvent.MOUSE_ENTERED,
                    event -> {
                        highlightHovered(p, GridPane.getColumnIndex(p), GridPane.getRowIndex(p));
                    });
        }

        for (Piece p : pieces2) {
            p.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
                paintDefault();
            });
        }

        for (Piece p : pieces1) {
            p.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                moveClickedPiece(p, GridPane.getColumnIndex(p), GridPane.getRowIndex(p));
            });
        }

        for (Piece p : pieces2) {
            p.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                moveClickedPiece(p, GridPane.getColumnIndex(p), GridPane.getRowIndex(p));
            });
        }
    }

}
