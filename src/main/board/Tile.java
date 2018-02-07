package main.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{

    int xCoordinate;

    int yCoordinate;

    int length;

    boolean hasPiece;

    boolean white;

    Color color;

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
            super.setFill(Color.web("0xFFF8E7"));
        } else {
            super.setFill(Color.web("0x47423c"));
        }
    }

    public void paintHighlight() {
        super.setFill(Color.web("0xFF6"));
    }
    public void setColor(Color color) {
        this.color = color;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

}
