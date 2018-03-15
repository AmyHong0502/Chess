package main;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import main.board.Board3D;
import main.console.ColourTheme;
import main.console.Console;
import main.board.Board;
import main.console.EventController;

public class Layout extends BorderPane {

    private Board topBoard;

    private Board middleBoard;

    private Board bottomBoard;

    private Board3D board3D;

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
        board3D = new Board3D(blackPlayer, whitePlayer, topBoard, middleBoard, bottomBoard, colourTheme);
        board3D.initialSetup();

        eventController = new EventController(bottomBoard, colourTheme);
        eventController.addColouringListener();
        console = new Console(topBoard, middleBoard, bottomBoard, blackPlayer, whitePlayer, colourTheme, eventController);
        console.initialSetup();

        this.setRight(console);
        this.setCenter(board3D);

        colourTheme.paintByTheme(topBoard, 0);
        colourTheme.paintByTheme(middleBoard, 1);
        colourTheme.paintByTheme(bottomBoard, 2);
    }

}
