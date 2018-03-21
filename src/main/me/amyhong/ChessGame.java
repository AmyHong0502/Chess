package me.amyhong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import me.amyhong.board.Board;
import me.amyhong.board.Board3D;
import me.amyhong.console.ColourTheme;
import me.amyhong.console.Console;
import me.amyhong.console.EventController;

public class ChessGame extends Application {

    Group root;

    private BorderPane borderPane;

    /** Boards on a 3D chessboard. */
    private Board[] boards;

    /** 3D chessboard. */
    private Board3D board3D;

    /** Player with black pieces. */
    private Player blackPlayer;

    /** Player with white pieces. */
    private Player whitePlayer;

    private Console console;

    /** ColourTheme object for colouring. */
    private ColourTheme colourTheme;

    private EventController eventController;

    public ChessGame() {
        root = new Group();

        borderPane = new BorderPane();

        blackPlayer = new Player(false);
        whitePlayer = new Player(true);

        colourTheme = new ColourTheme();

        boards = initializeBoards();
        board3D = new Board3D(blackPlayer, whitePlayer, boards);
        board3D.initialSetup();

        eventController = new EventController(boards, colourTheme, blackPlayer, whitePlayer);
        eventController.addColouringListeners();
        console = new Console(board3D, blackPlayer, whitePlayer, colourTheme, eventController);
        console.initialSetup();

        borderPane.setRight(console);
        borderPane.setCenter(board3D);

        colourTheme.paintByTheme(boards);

        root.getChildren().addAll(borderPane);
    }

    private Board[] initializeBoards() {
        Board topBoard = new Board(blackPlayer, whitePlayer, colourTheme, 0);
        Board middleBoard = new Board(blackPlayer, whitePlayer, colourTheme, 1);
        Board bottomBoard = new Board(blackPlayer, whitePlayer, colourTheme, 2);

        return new Board[] {topBoard, middleBoard, bottomBoard};
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 1587, 480));
        primaryStage.show();
    }

    /**
     * Drives the program.
     * 
     * @param args launches JavaFX application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
