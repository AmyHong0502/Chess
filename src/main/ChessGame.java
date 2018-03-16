package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.board.Board;
import main.board.Board3D;
import main.console.ColourTheme;
import main.console.Console;
import main.console.EventController;

public class ChessGame extends Application {

    Group root;

    private BorderPane borderPane;

    private Board topBoard;

    private Board middleBoard;

    private Board bottomBoard;

    private Board3D board3D;

    private Player blackPlayer;

    private Player whitePlayer;

    private Console console;

    private ColourTheme colourTheme;

    private EventController eventController;

    public ChessGame() {
        root = new Group();

        borderPane = new BorderPane();

        blackPlayer = new Player(false);
        whitePlayer = new Player(true);

        colourTheme = new ColourTheme();

        topBoard = new Board(blackPlayer, whitePlayer, colourTheme, 0);
        middleBoard = new Board(blackPlayer, whitePlayer, colourTheme, 1);
        bottomBoard = new Board(blackPlayer, whitePlayer, colourTheme, 2);
        board3D = new Board3D(blackPlayer, whitePlayer, topBoard, middleBoard, bottomBoard);
        board3D.initialSetup();

        eventController = new EventController(topBoard, middleBoard, bottomBoard, colourTheme, blackPlayer, whitePlayer);
        eventController.addColouringListener();
        console = new Console(topBoard, middleBoard, bottomBoard, blackPlayer, whitePlayer, colourTheme, eventController);
        console.initialSetup();

        borderPane.setRight(console);
        borderPane.setCenter(board3D);

        colourTheme.paintByTheme(topBoard, 0);
        colourTheme.paintByTheme(middleBoard, 1);
        colourTheme.paintByTheme(bottomBoard, 2);

        root.getChildren().addAll(borderPane);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, 1587, 480));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
