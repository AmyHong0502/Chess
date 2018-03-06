package main.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

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
            setFill(Color.web("0x2F3138"));
        }
    }

    void setMovableHighlight() {
        setFill(Color.web("0xF99"));
    }

    void setCapturableHighlight() {
        setFill(Color.web("0x6F6"));
    }

    public boolean isWhite() {
        return white;
    }

}
