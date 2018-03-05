package main.pieces;

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

    Color color;

    Color clickedColor;

    char type;

    boolean firstMove;

    Piece(final boolean white, final char type, int columnIndex, int rowIndex, boolean firstMove) {
        super();
        this.type = type;
        this.firstMove = firstMove;
        hover = false;
        clicked = false;
        this.white = white;
        color = white ? Color.WHITE : Color.BLACK;
        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        setFill(color);
        setFont(new Font(45));
        setTextAlignment(TextAlignment.CENTER);
        addClickListener();

        clickedColor = Color.web("0x00F");
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void addClickListener() {
        addEventFilter(MouseEvent.MOUSE_ENTERED, event -> hover = true);

        addEventFilter(MouseEvent.MOUSE_EXITED, event -> hover = false);

        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> clicked = !clicked);

        addEventFilter(MouseEvent.MOUSE_CLICKED, event -> highlightClickedPiece());
    }

    public void highlightClickedPiece() {
        if (clicked) {
            setFill(clickedColor);
        } else {
            setFill(color);
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

    public boolean isFirstMove() {return firstMove;}
}
