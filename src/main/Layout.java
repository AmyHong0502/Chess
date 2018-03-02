package main;

import javafx.scene.layout.BorderPane;
import main.SaveLoad.Console;
import main.board.Board;

public class Layout extends BorderPane {

    private Board board;

    private Player blackPlayer;

    private Player whitePlayer;

    private Console console;

    Layout() {
        blackPlayer = new Player(false);
        whitePlayer = new Player(true);
        board = new Board(blackPlayer, whitePlayer);
        board.setStyle("-fx-background-color: #066;");
        console = new Console(board, blackPlayer, whitePlayer);
        console.initialSetup();

        this.setRight(console);
        this.setCenter(board);
    }

}
