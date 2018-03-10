package main;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import main.console.ColourTheme;
import main.console.Console;
import main.board.Board;
import main.console.EventController;

public class Layout extends BorderPane {

    private Board topBoard;

    private Board middleBoard;

    private Board bottomBoard;

    private Player blackPlayer;

    private Player whitePlayer;

    private Console console;

    private ColourTheme colourTheme;

    private EventController eventController;

    Layout() {
        blackPlayer = new Player(false);
        whitePlayer = new Player(true);
        colourTheme = new ColourTheme();
        topBoard = new Board(blackPlayer, whitePlayer, colourTheme, 0);
        middleBoard = new Board(blackPlayer, whitePlayer, colourTheme, 1);
        bottomBoard = new Board(blackPlayer, whitePlayer, colourTheme, 2);

        eventController = new EventController(bottomBoard, colourTheme);
        eventController.addColouringListener();
        console = new Console(topBoard, middleBoard, bottomBoard, blackPlayer, whitePlayer, colourTheme, eventController);
        console.initialSetup();

        HBox hBox = new HBox();
        hBox.getChildren().add(0, topBoard);
        hBox.getChildren().add(1, middleBoard);
        hBox.getChildren().add(2, bottomBoard);

        this.setRight(console);
        this.setCenter(hBox);

        colourTheme.paintByTheme(topBoard);
        colourTheme.paintByTheme(middleBoard);
        colourTheme.paintByTheme(bottomBoard);
    }

}
