package main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.board.Board;
import main.board.Tile;
import main.pieces.Piece;

import java.util.ArrayList;

public class ChessGame extends Application {

    private Group root;

    private Board board;

    private Player player1;

    private Player player2;

    private int boardLength;

    public ChessGame() {
        root = new Group();
        board = new Board();
        player1 = new Player(false);
        player2 = new Player(true);

        boardLength = board.getBoardLength();

        root.getChildren().addAll(board);
        initializePieces();
    }

    public void initializePieces() {
        ArrayList<Piece> pieces = player1.getPieces();
        int pieceCount = 0;

        for (int row = 6; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                Piece piece = pieces.get(pieceCount);
                pieceCount++;

                piece.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> System.out.println(
                                "row " + GridPane.getRowIndex(piece)
                                        + ", col: " + GridPane.getColumnIndex(piece)));
              /*  piece.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            highlightMovable(piece.movable());
                        });*/

                board.add(piece, column, row);
            }
        }
    }

    private void highlightMovable(final int[][] movable) {
        System.out.println("length: " + movable.length);
        for (int i = 0; i < movable.length; i++) {
            int x = movable[i][0];
            int y = movable[i][1];

            System.out.println("x: " + x + ", y: " + y);
            board.getChildren().forEach(
                    (Node c) -> {
                        if (c.getClass().equals("main.board.Tile")) {
                            if (((Tile) c).getxCoordinate() == x && ((Tile) c).getyCoordinate() == y) {
                                board.paintDefault();
                ((Tile) c).paintHighlight();
                            }
                        }
                    }
            );
        }
    }

    /**
     * Returns a piece if found by index. Otherwise returns null.
     * @param x
     * @param y
     * @return
     */
    public Piece findPieceByIndex(final int x, final int y) {
        Piece result;

        ObservableList<Node> children = board.getChildren();

        for (Node node : children) {
            if (node.getClass().equals("main.pieces.Piece")) {
                if (GridPane.getRowIndex(node) == x && GridPane.getRowIndex(node) == y) {
                    return (Piece) node;
                }
            }
        }

        return null;
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
