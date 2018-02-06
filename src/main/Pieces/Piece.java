package main.pieces;

import javafx.scene.paint.Color;
import main.Movement;

public abstract class Piece implements Movement {

    char type;

    int xCoordinate;

    int yCoordinate;

    boolean white;

    Color color;

    Piece(boolean white) {
        this.white = white;
        color = white ? Color.web("0xFFF") : Color.web("0x000");
    }

    public void setXCoordinate(int x) {
        xCoordinate = x;
    }

    public void setYCoordinate(int y) {
        yCoordinate = y;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }


    public Color getColor() {
        return color;
    }

    public void setType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }

    public abstract int[][] movable();

    public abstract int[][] catchable();
}
