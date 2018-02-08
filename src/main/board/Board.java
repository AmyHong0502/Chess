package main.board;

import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import main.pieces.Pawn;

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
