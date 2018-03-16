package main.board;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.Player;
import main.console.ColourTheme;
import main.pieces.King;
import main.pieces.Pawn;
import main.pieces.Piece;
import main.pieces.Rook;

import java.util.ArrayList;
import java.util.Iterator;

public class Board3D extends HBox {

    /** Clicked piece to move or capture other player's piece. */
    private Piece clickedPiece;

    /** Board on the top level. verticalLevel of the board is 0. */
    private Board topBoard;

    /** Board on the middle level. verticalLevel of the board is 1. */
    private Board middleBoard;

    /** Board on the bottom level. verticalLevel of the board is 2. */
    private Board bottomBoard;

    /** Player with black pieces. */
    private Player blackPlayer;

    /** Player with white pieces. */
    private Player whitePlayer;

    /**
     * Constructor of this Board3D.
     * 
     * @param blackPlayer Player with black pieces
     * @param whitePlayer Player with white pieces
     * @param topBoard    Board on the top level
     * @param middleBoard Board on the middle level
     * @param bottomBoard Board on the bottom level
     */
    public Board3D(Player blackPlayer, Player whitePlayer,
                   Board topBoard, Board middleBoard, Board bottomBoard) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;

        this.topBoard = topBoard;
        this.middleBoard = middleBoard;
        this.bottomBoard = bottomBoard;

        getChildren().add(0, topBoard);
        getChildren().add(1, middleBoard);
        getChildren().add(2, bottomBoard);

        clickedPiece = null;
    }

    /**
     * Gives every piece and tile a click-listener and draw all pieces 
     * on top, middle, and bottom board.
     */
    public void initialSetup() {
        drawPieces();
        initializeClickListener(blackPlayer.getPieces(), whitePlayer.getPieces());
    }

    /**
     * Draws pieces on top, middle, and bottom board.
     */
    private void drawPieces() {
        topBoard.drawPieces();
        middleBoard.drawPieces();
        bottomBoard.drawPieces();
    }

    /**
     * Gives click-listener every tile and piece for 
     * selecting and moving pieces.
     * 
     * @param blackPieces Black pieces
     * @param whitePieces White pieces
     */
    private void initializeClickListener(final ArrayList<Piece> blackPieces, 
                                         final ArrayList<Piece> whitePieces) {
        for (Piece p : blackPieces) {
            p.addEventFilter(MouseEvent.MOUSE_CLICKED,
                    event -> selectPiece(p));
        }

        for (Piece p : whitePieces) {
            p.addEventFilter(MouseEvent.MOUSE_CLICKED,
                    event -> selectPiece(p));
        }

        for (Node node : topBoard.getChildren()) {
            if (node.getClass().equals(Tile.class)) {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> tryToMoveClickedPiece(((Tile) node).getVerticalLevel(),
                                GridPane.getColumnIndex(node), GridPane.getRowIndex(node)));
            }
        }
        for (Node node : middleBoard.getChildren()) {
            if (node.getClass().equals(Tile.class)) {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> tryToMoveClickedPiece(((Tile) node).getVerticalLevel(),
                                GridPane.getColumnIndex(node), GridPane.getRowIndex(node)));
            }
        }
        for (Node node : bottomBoard.getChildren()) {
            if (node.getClass().equals(Tile.class)) {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> tryToMoveClickedPiece(((Tile) node).getVerticalLevel(),
                                GridPane.getColumnIndex(node), GridPane.getRowIndex(node)));
            }
        }
    }

    /** 
     * If a piece is selected during its owner's turn, 
     * it will be ready to move or capture other players piece. 
     * Otherwise checks whether the user can capture the piece or not.
     * 
     * @param piece piece to move or capture other players piece
     *              or piece to capture
     */
    private void selectPiece(Piece piece) {
        boolean turn = piece.isWhite() ? whitePlayer.isMyTurn() : blackPlayer.isMyTurn();

        if (turn) {
            clickedPiece = piece;
        } else {
            tryToCapture(piece);
        }
    }

    /**
     * Tries to capture other player's piece. 
     * If it is legal move, this method captures the given prey.
     * Otherwise this method does nothing.
     * 
     * @param prey a piece to capture
     */
    private void tryToCapture(Piece prey) {
        if (clickedPiece == null) {
            return;
        }

        final int predatorVerticalLevel = clickedPiece.getVerticalLevel();
        final int preyVerticalLevel = prey.getVerticalLevel();

        if (isAllowedToMove(clickedPiece.capturable(), 
                            predatorVerticalLevel, preyVerticalLevel,
                            prey.getColumnIndex(), prey.getRowIndex())) {
            capturePrey(prey.getVerticalLevel(), prey.getColumnIndex(), prey.getRowIndex());
        }
    }

    /**
     * Captures a piece and move to the prey's location. 
     * A player consumes their turn to capture the given prey.
     * 
     * @param preyVerticalLevel vertical-3D level of a piece to capture
     * @param preyColumnIndex   column index of a piece to capture
     * @param preyRowIndex      row index of a piece to capture
     */
    private void capturePrey(final int preyVerticalLevel,
                             final int preyColumnIndex, final int preyRowIndex) {
        if (clickedPiece == null) {
            return;
        }

        Piece prey = null;

        switch (preyVerticalLevel) {
            case 0:
                prey = topBoard.findPieceByIndex(preyColumnIndex, preyRowIndex);
                break;
            case 1:
                prey = middleBoard.findPieceByIndex(preyColumnIndex, preyRowIndex);
                break;
            case 2:
                prey = bottomBoard.findPieceByIndex(preyColumnIndex, preyRowIndex);
                break;
        }

        ArrayList<Piece> preyPieces = clickedPiece.isWhite() ? blackPlayer.getPieces()
                                                             : whitePlayer.getPieces();

        Iterator itr = preyPieces.iterator();
        while (itr.hasNext()) {
            Piece piece = (Piece) itr.next();
            if (piece.equals(prey)) {
                itr.remove();
            }
        }

        moveClickedPiece(preyVerticalLevel, preyColumnIndex, preyRowIndex);
        refreshPiecesOnBoards();
        switchTurn();
    }

    /**
     * Returns true if it is allowed to move to given location.
     * 
     * @param movable           array of allowed locations 
     * @param srcVerticalLevel  starting vertical-3D level
     * @param destVerticalLevel destination's vertical-3D level
     * @param destColumnIndex   destination's column index
     * @param destRowIndex      destination's row index
     * @return                  true if it is allowed to move to given location
     */
    private boolean isAllowedToMove(final int[][] movable,
                                    final int srcVerticalLevel, final int destVerticalLevel,
                                    final int destColumnIndex, final int destRowIndex) {
        if (Math.abs(srcVerticalLevel - destVerticalLevel) > 1) {
            return false;
        }

        for (int[] cell : movable) {
            if (cell[0] == destColumnIndex && cell[1] == destRowIndex) {
                return true;
            }
        }
        return false;
    }

    /**
     * Ends current turn and starts the other user's turn.
     */
    private void switchTurn() {
        ColourTheme ct = new ColourTheme();

        ct.unhighlightPieces(whitePlayer.getPieces());
        ct.unhighlightPieces(blackPlayer.getPieces());

        if (whitePlayer.isMyTurn()) {
            whitePlayer.finishMyTurn();
            blackPlayer.startMyTurn();
        } else {
            blackPlayer.finishMyTurn();
            whitePlayer.startMyTurn();
        }
    }

    /**
     * Tries to move clicked piece to given location.
     * If it is legal move, this method moves clickedPiece to given location.
     * Otherwise this method does nothing.
     * 
     * @param destVerticalLevel destination's vertical level
     * @param destColumnIndex   destination's column index
     * @param destRowIndex      destination's row index
     */
    private void tryToMoveClickedPiece(final int destVerticalLevel,
                                       final int destColumnIndex, final int destRowIndex) {
        if (clickedPiece == null) {
            return;
        }

        boolean turn = clickedPiece.isWhite() ? whitePlayer.isMyTurn()
                : blackPlayer.isMyTurn();

        if (turn) {
            final int[][] movable = clickedPiece.movable();

            if (isAllowedToMove(movable, 
                                clickedPiece.getVerticalLevel(), destVerticalLevel, 
                                destColumnIndex, destRowIndex)) {
                moveClickedPiece(destVerticalLevel, destColumnIndex, destRowIndex);
                switchTurn();
            }
        }

    }

    /**
     * Moves clickedPiece to given location.
     * 
     * @param destVerticalLevel destination's vertical Level
     * @param destColumnIndex   destination's column index
     * @param destRowIndex      destination's row index
     */
    private void moveClickedPiece(final int destVerticalLevel,
                                  final int destColumnIndex, final int destRowIndex) {
        clickedPiece.setVerticalLevel(destVerticalLevel);
        clickedPiece.setColumnIndex(destColumnIndex);
        clickedPiece.setRowIndex(destRowIndex);

        if (clickedPiece.getClass().equals(Pawn.class)
                || clickedPiece.getClass().equals(Rook.class)
                || clickedPiece.getClass().equals(King.class)) {
            clickedPiece.setNeverMoved(false);
        }

        clickedPiece = null;
        refreshPiecesOnBoards();
    }

    /**
     * Redraw pieces on every board: top, middle, and bottom.
     */
    public void refreshPiecesOnBoards() {
        topBoard.refreshPieces();
        middleBoard.refreshPieces();
        bottomBoard.refreshPieces();
    }

}
