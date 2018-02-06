package main;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.board.Board;
import main.pieces.Pawn;

public class ChessGame extends Application {

    private Group root;

    private Board board;

    private GridPane pane;

    Player player1;

    Player player2;

    private int boardLength;

    public ChessGame() {
        root = new Group();
        board = new Board();
        pane = board.getPane();
        player1 = new Player(true);
        player2 = new Player(false);

        boardLength = board.getBoardLength();

        root.getChildren().addAll(pane);
        drawPieces();
    }

    public void drawPieces() {
        char type;
        Color color;
        for (int row = 6; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                type = player1.getPieces().get(column).getType();
                color = player1.getColor();

                Text text = new Text(Character.toString(type));

                text.setFont(new Font(40));
                text.setFill(color);
                text.setTextAlignment(TextAlignment.CENTER);


                text.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> System.out.println(
                                "row " + pane.getRowIndex(text)
                                        + ", col: " + pane.getColumnIndex(text)));

                pane.add(text, column, row);
            }
        }
    }

    private void alignPane() {
        pane.setAlignment(Pos.CENTER);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chess");
        primaryStage.setScene(new Scene(root, boardLength, boardLength));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
