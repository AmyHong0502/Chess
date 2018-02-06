package main;

import javafx.scene.paint.Color;

public class Player {

    Color color;

    Player(boolean white) {

        if (white) {
            setWhite();
        } else {
            setBlack();
        }

    }

    public void setBlack() {
        color = Color.web("#231704");
    }

    public void setWhite() {
        color = Color.FLORALWHITE;
    }

}
