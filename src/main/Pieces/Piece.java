package main.pieces;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.Movement;

import java.io.Serializable;

public abstract class Piece extends Text implements Movement, Serializable {
    boolean white;

    boolean hover;

    boolean clicked;

    Color color;

    Color clickedColor;

    Piece(boolean white) {
        super();
        hover = false;
        clicked = false;
        this.white = white;
        color = white ? Color.WHITE : Color.BLACK;
        setFill(color);
        setFont(new Font(45));
        setTextAlignment(TextAlignment.CENTER);
        addClickListener();

        clickedColor = Color.web("0x00F");
    }

    public boolean isClicked() {
        return clicked;
    }

    public void addClickListener() {
        addEventFilter(MouseEvent.MOUSE_ENTERED,
                event ->
                        System.out.println(
                                "IN PIECE: " +
                                        "row " + GridPane.getRowIndex(this)
                                        + ", col: " + GridPane.getColumnIndex(this)));

        addEventFilter(MouseEvent.MOUSE_ENTERED, event -> hover = true);

        addEventFilter(MouseEvent.MOUSE_EXITED,
                event ->
                        System.out.println(
                                "OUT PIECE: " +
                                        "row " + GridPane.getRowIndex(this)
                                        + ", col: " + GridPane.getColumnIndex(this)));

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
}
