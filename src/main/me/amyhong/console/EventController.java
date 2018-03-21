package me.amyhong.console;

import javafx.scene.input.MouseEvent;
import me.amyhong.Player;
import me.amyhong.board.Board;
import me.amyhong.pieces.*;

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

    /**
     * Constructor of this EventController.
     * @param boards      boards on this chess game
     * @param colourTheme ColourTheme object for colouring
     * @param blackPlayer player with black pieces
     * @param whitePlayer player with white pieces
     */
    public EventController(Board[] boards, ColourTheme colourTheme,
                           Player blackPlayer, Player whitePlayer) {
        this.boards = boards;
        this.colourTheme = colourTheme;
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
    }

    /**
     * Highlights a given piece if the owner's turn.
     *
     * @param piece         piece to highlight
     * @param verticalLevel vertical level of the given piece
     */
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
        addColouringListenersOnPieces(boards, blackPlayer.getPieces());
        addColouringListenersOnPieces(boards, whitePlayer.getPieces());
    }

    /**
     * Adds highlighter and unhighlighter for mouse click & hover on pieces.
     *
     * @param boards boards to add colouring listeners
     * @param pieces pieces to add colouring listeners
     */
    private void addColouringListenersOnPieces(Board[] boards, ArrayList<Piece> pieces) {
        for (Piece piece : pieces) {
            piece.addEventFilter(MouseEvent.MOUSE_CLICKED,
                    event -> tryHighlightingPiece(piece, piece.getVerticalLevel()));
            for (int i = 0; i < boards.length; i++) {
                int finalI = i;
                piece.addEventFilter(MouseEvent.MOUSE_ENTERED,
                        event -> boards[finalI].highlightTiles(piece));
                piece.addEventFilter(MouseEvent.MOUSE_EXITED,
                        event -> colourTheme.unhighlightTiles(boards[finalI], finalI));
            }
        }
    }
}
