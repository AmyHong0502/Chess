package main.pieces;

import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.Movement;

public abstract class Piece extends Text implements Movement {
    boolean white;

    boolean hover;

    boolean clicked;

    private int columnIndex;

    private int rowIndex;

    char type;

    boolean firstMove;

    Color colorTheme1White;
    Color colorTheme1Black;
    Color colorTheme1Clicked;
    Color colorTheme2White;
    Color colorTheme2Black;
    Color colorTheme2Clicked;
    Color colorTheme3White;
    Color colorTheme3Black;
    Color colorTheme3Clicked;

    Piece(final boolean white, final char type, int columnIndex, int rowIndex, boolean firstMove, final int colourTheme) {
        super();
        this.type = type;
        this.firstMove = firstMove;
        hover = false;
        clicked = false;
        this.white = white;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;

        colorTheme1White = Color.web("0xFFF");
        colorTheme1Black = Color.web("0x000");
        colorTheme1Clicked = Color.web("0x3885CA");
        colorTheme2White = Color.web("0xFFF");
        colorTheme2Black = Color.web("0x000");
        colorTheme2Clicked = Color.web("0x3885CA");
        colorTheme3White = Color.web("0xFFF");
        colorTheme3Black = Color.web("0x000");
        colorTheme3Clicked = Color.web("0x3885CA");

        decoratePiece(colourTheme);
        addClickListener();
    }

    private void decoratePiece(final int colourTheme) {
        paintColour(colourTheme);

        setFont(new Font(45));
        setTextAlignment(TextAlignment.CENTER);

        DropShadow ds = new DropShadow();
        ds.setOffsetX(3.0);
        ds.setOffsetY(3.0);
        ds.setColor(Color.web("0x889"));
        setEffect(ds);
    }

    public void paintColour(final int colourTheme) {
        final Color colour;
        switch (colourTheme) {
            case 1:
                colour = white ? colorTheme1White : colorTheme1Black;
                break;
            case 2:
                colour = white ? colorTheme2White : colorTheme2Black;
                break;
            case 3:
            default:
                colour = white ? colorTheme3White : colorTheme3Black;
                break;
        }
        setFill(colour);
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void addClickListener() {
        addEventFilter(MouseEvent.MOUSE_ENTERED, event -> hover = true);

        addEventFilter(MouseEvent.MOUSE_EXITED, event -> hover = false);

        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> clicked = !clicked);

        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> highlightClickedPiece());
    }

    public void highlightClickedPiece() {
        if (clicked) {
            setFill(colorTheme1Clicked);
        } else {
            Color defaultColour = isWhite() ? colorTheme1White : colorTheme1Black;
            setFill(defaultColour);
        }
    }

    public void setColumnIndex(int colIndex) {
        this.columnIndex = colIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public char getType() {
        return type;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isFirstMove() {
        return firstMove;
    }
}
