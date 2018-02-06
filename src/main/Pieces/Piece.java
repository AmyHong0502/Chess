package main.Pieces;

public abstract class Piece {

    char type;

    int xCoordinate;

    int yCoordinate;

    boolean white;

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

    public void setColor(boolean white) {
        this.white = white;
    }

    public void setType(char type) {
        this.type = type;
    }

    public abstract int[][] moveable();

    public abstract int[][] catchable();
}
