package main.board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    int xCoordinate;

    int yCoordinate;

    int length;

    boolean hasPiece;

    boolean white;

    Color colorTheme1Light;
    Color colorTheme1Dark;
    Color colorTheme2Light;
    Color colorTheme2Dark;
    Color colorTheme3Light;
    Color colorTheme3Dark;

    boolean highlighted;

    public Tile(int x, int y, int length, boolean hasPiece, boolean white, final int colourTheme) {
        super(length, length);
        xCoordinate = x;
        yCoordinate = y;
        this.length = length;
        this.hasPiece = hasPiece;
        this.white = white;

        colorTheme1Light = Color.web("0xFFF8E7");
        colorTheme1Dark = Color.web("0x2F3138");
        colorTheme2Light = Color.web("0xBBB");
        colorTheme2Dark = Color.web("0x555");
        colorTheme3Light = Color.web("0xDDD");
        colorTheme3Dark = Color.web("0x333");

        setColor(white, colourTheme);
    }

    public void setColor(final boolean white, final int colourTheme) {
        final Color colour;
        switch (colourTheme) {
            case 1:
                colour = white ? colorTheme1Light : colorTheme1Dark;
                break;
            case 2:
                colour = white ? colorTheme2Light : colorTheme2Dark;
                break;
            case 3:
            default:
                colour = white ? colorTheme3Light : colorTheme3Dark;
                break;
        }
        setFill(colour);
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
