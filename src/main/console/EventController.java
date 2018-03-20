package main.console;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import main.Player;
import main.board.Board;
import main.pieces.*;

import java.util.ArrayList;

public class EventController {

    /** Player with black pieces. */
    private Player blackPlayer;

    /** Player with white pieces. */
    private Player whitePlayer;

    /** Chess boards on the 3D chessboard. */
    private Board[] boards;

    /** ColourTheme object for colouring. */
    private ColourTheme colourTheme;

    public EventController(Board[] boards, ColourTheme colourTheme, Player blackPlayer, Player whitePlayer) {
        this.boards = boards;
        this.colourTheme = colourTheme;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
    }

    private void tryHighlightingPiece(Piece piece, int verticalLevel) {
        boolean white = piece.isWhite();
        boolean turn;
        ArrayList<Piece> pieces;

        if (white) {
            turn = whitePlayer.isMyTurn();
            pieces = whitePlayer.getPieces();
        } else {
            turn = blackPlayer.isMyTurn();
            pieces = blackPlayer.getPieces();
        }

        for (Piece p : pieces) {
            colourTheme.unhighlightPiece(p, p.getVerticalLevel());
        }

        if (turn) {
            colourTheme.highlightPiece(piece, verticalLevel);
        }
    }

    public void addColouringListeners() {
        for (int i = 0; i < boards.length; i++) {
            addColouringListeners(boards[i]);
        }
    }

    private void addColouringListeners(Board board) {
        for (Node node : board.getChildren()) {
            if (Piece.isPiece(node)) {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> tryHighlightingPiece((Piece) node, ((Piece) node).getVerticalLevel()));
                node.addEventFilter(MouseEvent.MOUSE_ENTERED,
                        event -> board.highlightTiles((Piece) node));
                node.addEventFilter(MouseEvent.MOUSE_EXITED,
                        event -> colourTheme.unhighlightTiles(board, ((Piece) node).getVerticalLevel()));
            }
        }
    }

}
