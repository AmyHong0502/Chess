package main.board;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.Player;
import main.pieces.Piece;

import java.util.ArrayList;

public class Board extends GridPane {

    public static int NUMBER_OF_CELLS = 8;

    private int tileLength;

    private int boardLength;

    private Color color;

    public Board(Player player1, Player player2) {
        buildBoard();
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);
        initializePieces(player1, player2);
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

    public void buildBoard() {
        for (int row = 0; row < NUMBER_OF_CELLS; row++) {
            for (int column = 0; column < NUMBER_OF_CELLS; column++) {
                fillBackgroundColumn();
                fillBackgroundRow();

                boolean white = (row + column) % 2 == 0;

                tileLength = 60;
                boardLength = tileLength * NUMBER_OF_CELLS;

                Tile tile = new Tile(column, row, tileLength, false, white);

                add(tile, column, row);
            }
        }
    }

    private void initializePieces(Player player1, Player player2) {
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

        initializeHighlighter(player1.getPieces(), player2.getPieces());
    }

    public Tile findTileByIndex(final int columnIndex, final int rowIndex) {
        for (Node node : getChildren()) {
            if (node.getClass().equals(new Tile(0, 0, 0, false, false).getClass())) {
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
                            if (c.getClass().equals(new Tile(0, 0, 0, false, false).getClass())) {
                                ((Tile) c).setColor(((Tile) c).white);
                            }
                        }
                );
            }
        }
    }

    private void initializeHighlighter(final ArrayList<Piece> pieces1, final ArrayList<Piece> pieces2) {
        for (Piece p : pieces1) {
            p.addEventFilter(MouseEvent.MOUSE_ENTERED,
                    event -> {
                        highlightMovable(p, GridPane.getColumnIndex(p), GridPane.getRowIndex(p));
                    });
        }

        for (Piece p : pieces1) {
            p.addEventFilter(MouseEvent.MOUSE_EXITED,
                    event -> {
                        paintDefault();
                    });
        }

        for (Piece p : pieces2) {
            p.addEventFilter(MouseEvent.MOUSE_ENTERED,
                    event -> {
                        highlightMovable(p, GridPane.getColumnIndex(p), GridPane.getRowIndex(p));
                    });
        }

        for (Piece p : pieces2) {
            p.addEventFilter(MouseEvent.MOUSE_EXITED,
                    event -> {
                        paintDefault();
                    });
        }
    }

    private void highlightMovable(Piece piece, int columnIndex, int rowIndex) {
        int[][] movable = piece.movable(columnIndex, rowIndex);

        for (int i = 0; i < movable.length; i++) {
            int col = movable[i][0];
            int row = movable[i][1];

            Tile tile = findTileByIndex(col, row);
            tile.paintHighlight(piece, columnIndex, rowIndex);
        }
    }
}
