package main;

import javafx.scene.layout.BorderPane;
import main.console.ColourTheme;
import main.console.Console;
import main.board.Board;
import main.console.EventController;

public class Layout extends BorderPane {

    private Board board;

    private Player blackPlayer;

    private Player whitePlayer;

    private Console console;

    private ColourTheme colourTheme;

    private EventController eventController;

    Layout() {
        blackPlayer = new Player(false);
        whitePlayer = new Player(true);
        colourTheme = new ColourTheme();
        board = new Board(blackPlayer, whitePlayer, colourTheme);
        console = new Console(board, blackPlayer, whitePlayer, colourTheme);
        console.initialSetup();

        eventController = new EventController(board, colourTheme);
        eventController.addColouringListener();

        this.setRight(console);
        this.setCenter(board);

        colourTheme.paintByTheme(board);
    }

}
