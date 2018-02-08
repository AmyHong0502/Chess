package main.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.pieces.Piece;

public class Tile extends Rectangle{

    int xCoordinate;

    int yCoordinate;

    int length;

    boolean hasPiece;

    boolean white;

    Color color;

    boolean highlighted;

    public Tile(int x, int y, int length, boolean hasPiece, boolean white) {
        super(length, length);
        xCoordinate = x;
        yCoordinate = y;
        this.length = length;
        this.hasPiece = hasPiece;
        this.white = white;
        setColor(white);
    }

    public void setColor(boolean white) {
        if (white) {
            setFill(Color.web("0xFFF8E7"));
        } else {
            setFill(Color.web("0x47423c"));
        }
    }

    private void setHighlight() {
        setFill(Color.web("0xFF6"));
    }

    public void paintHighlight(final Piece piece, final int columnIndex, final int rowIndex) {
        piece.movable(columnIndex, rowIndex);
        setHighlight();
    }

    public boolean isWhite() {
        return white;
    }

}
