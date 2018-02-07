package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import main.board.Board;
import main.board.Tile;
import main.pieces.Piece;

public class ChessGame extends Application {

    private Group root;

    private Board board;

    Player player1;

    Player player2;

    private int boardLength;

    public ChessGame() {
        root = new Group();
        board = new Board();
        player1 = new Player(true);
        player2 = new Player(false);

        boardLength = board.getBoardLength();

        root.getChildren().addAll(board);
        drawPieces();
    }

    public void drawPieces() {
        char type;
        Color color;
        for (int row = 6; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Piece piece = player1.getPieces().get(column);
                type = piece.getType();
                color = piece.getColor();

                Text text = new Text(Character.toString(type));

                text.setFont(new Font(40));
                text.setFill(color);
                text.setTextAlignment(TextAlignment.CENTER);

                text.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> System.out.println(
                                "row " + board.getRowIndex(text)
                                        + ", col: " + board.getColumnIndex(text)));
                text.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            findChildren(piece.movable());
                        });

                board.add(text, column, row);
            }
        }
    }

    public void highlightMovable(int[][] movable) {
        if (movable.length == 0)
            return;

        for (int cell = 0; cell < movable.length; cell++) {
            int x = movable[cell][0];
            int y = movable[cell][1];
        }

    }

    private void findChildren(final int[][] movable) {
        for (int i = 0; i < movable.length; i++) {
            int x = movable[i][0];
            int y = movable[i][1];

            System.out.println("x: " + x + ", y: " + y);

            board.getChildren().forEach(
                    (Node c) -> {
                        if (c.getClass().equals(new Tile(0, 0 , 0, false, false).getClass())) {
                            System.out.println(c.toString());

                            if (((Tile) c).getxCoordinate() == x && ((Tile) c).getyCoordinate() == y) {
                ((Tile) c).paintHighlight();
                            }

                        }
                    }
            );
        }
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
