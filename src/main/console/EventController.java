package main.console;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import main.board.Board;
import main.pieces.*;

public class EventController {

    /**
     * Chess board on the top of this gameboard.
     */
    private Board topBoard;

    /**
     * Chess board on the middle level of this gameboard.
     */
    private Board middleBoard;

    /**
     * Chess board on the bottom of this gameboard.
     */
    private Board bottomBoard;

    private ColourTheme colourTheme;

    public EventController(Board topBoard, Board middleBoard, Board bottomBoard, ColourTheme colourTheme) {
        this.topBoard = topBoard;
        this.middleBoard = middleBoard;
        this.bottomBoard = bottomBoard;
        this.colourTheme = colourTheme;
    }

    private static boolean isPiece(Object obj) {
        return obj.getClass().equals(Piece.class)
                || obj.getClass().equals(Pawn.class)
                || obj.getClass().equals(Knight.class)
                || obj.getClass().equals(Rook.class)
                || obj.getClass().equals(Bishop.class)
                || obj.getClass().equals(Queen.class)
                || obj.getClass().equals(King.class);
    }

    private void addColouringListener(Board board) {
        for (Node node : board.getChildren()) {
            if (isPiece(node)) {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> colourTheme.highlightPiece((Piece) node, ((Piece) node).getVerticalLevel()));
                node.addEventFilter(MouseEvent.MOUSE_ENTERED,
                        event -> board.highlightTiles((Piece) node));
                node.addEventFilter(MouseEvent.MOUSE_EXITED,
                        event -> colourTheme.unhighlightTiles(board, ((Piece) node).getVerticalLevel()));
            }
        }
    }

    public void addColouringListener() {
        addColouringListener(topBoard);
        addColouringListener(middleBoard);
        addColouringListener(bottomBoard);
    }
}
