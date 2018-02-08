package main.board;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.Player;
import main.pieces.Pawn;
import main.pieces.Piece;

import java.util.ArrayList;

public class Board extends GridPane {

    public static int NUMBER_OF_CELLS = 8;

    private int tileLength;

    private int boardLength;

    private Color color;

    public Board() {
        buildBoard();
        setMaxWidth(Double.MAX_VALUE);
        setMaxHeight(Double.MAX_VALUE);
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

    public void initializePieces(Player player1, Player player2) {
        ArrayList<Piece> pieces = player1.getPieces();
        int pieceCount = 0;

        for (int row = 6; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Piece piece = pieces.get(pieceCount);
                pieceCount++;

                piece.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> System.out.println(
                                "row " + GridPane.getRowIndex(piece)
                                        + ", col: " + GridPane.getColumnIndex(piece)));
              /*  piece.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            highlightMovable(piece.movable());
                        });*/

                add(piece, column, row);
            }
        }
    }

    /**
     * Returns a piece if found by index. Otherwise returns null.
     * @param x
     * @param y
     * @return
     */
    public Piece findPieceByIndex(final int x, final int y) {
        Piece result;

        ObservableList<Node> children = getChildren();

        for (Node node : children) {
            if (node.getClass().equals("main.pieces.Piece")) {
                if (GridPane.getRowIndex(node) == x && GridPane.getRowIndex(node) == y) {
                    return (Piece) node;
                }
            }
        }

        return null;
    }


    private void highlightMovable(final int[][] movable) {
        System.out.println("length: " + movable.length);
        for (int i = 0; i < movable.length; i++) {
            int x = movable[i][0];
            int y = movable[i][1];

            System.out.println("x: " + x + ", y: " + y);
            getChildren().forEach(
                    (Node c) -> {
                        if (c.getClass().equals("main.board.Tile")) {
                            if (((Tile) c).getxCoordinate() == x && ((Tile) c).getyCoordinate() == y) {
                                paintDefault();
                                ((Tile) c).paintHighlight();
                            }
                        }
                    }
            );
        }
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

    public void setBlack() {
        color = Color.web("#231704");
    }

    public void setWhite() {
        color = Color.FLORALWHITE;
    }

    public void highlightMovable(Pawn piece, int x, int y) {
        int[][] movable = piece.movable(x, y);

    }
}
