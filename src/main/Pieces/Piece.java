package main.pieces;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.Movement;

public abstract class Piece extends Text implements Movement {
    boolean white;

    boolean selected;

    Color color;

    Piece(boolean white) {
        super();
        this.white = white;
        color = white ? Color.WHITE : Color.BLACK;
        setFill(color);
        setFont(new Font(45));
        setTextAlignment(TextAlignment.CENTER);
    }

}
