package main.pieces;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.Movement;

public abstract class Piece extends Text implements Movement {
    boolean white;

    boolean hover;

    Color color;

    Piece(boolean white) {
        super();
        hover = false;
        this.white = white;
        color = white ? Color.WHITE : Color.BLACK;
        setFill(color);
        setFont(new Font(45));
        setTextAlignment(TextAlignment.CENTER);
        addClickListener();
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
    }

}
