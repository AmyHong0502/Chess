package main.console;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import main.board.Board;
import main.pieces.*;

public class EventController {

    private Board board;

    private ColourTheme colourTheme;

    public EventController(Board board, ColourTheme colourTheme) {
        this.board = board;
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

    public void addColouringListener() {
        for (Node node: board.getChildren()) {
            if (isPiece(node)) {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> colourTheme.highlightPiece((Piece) node));
                node.addEventFilter(MouseEvent.MOUSE_ENTERED,
                        event -> board.highlightTiles((Piece) node));
                node.addEventFilter(MouseEvent.MOUSE_EXITED,
                        event -> colourTheme.unhighlightTiles(board));
            }
        }
    }
}
