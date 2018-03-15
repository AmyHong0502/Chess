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

public class Board3D extends HBox {

    private Piece clickedPiece;

    private Board topBoard;

    private Board middleBoard;

    private Board bottomBoard;

    private Player blackPlayer;

    private Player whitePlayer;

    private ColourTheme colourTheme;

    public Board3D(Player blackPlayer, Player whitePlayer,
                   Board topBoard, Board middleBoard, Board bottomBoard,
                   ColourTheme colourTheme) {
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;

        this.topBoard = topBoard;
        this.middleBoard = middleBoard;
        this.bottomBoard = bottomBoard;

        getChildren().add(0, topBoard);
        getChildren().add(1, middleBoard);
        getChildren().add(2, bottomBoard);

        this.colourTheme = colourTheme;
        clickedPiece = null;
    }

    public void initialSetup() {
        drawPieces();
        initializeClickListener(blackPlayer.getPieces(), whitePlayer.getPieces());
    }

    private void drawPieces() {
        topBoard.drawPieces();
        middleBoard.drawPieces();
        bottomBoard.drawPieces();
    }

    private void initializeClickListener(final ArrayList<Piece> blackPieces, final ArrayList<Piece> whitePieces) {
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
                        event -> tryToMoveClickedPiece(((Tile) node).getzLevel(),
                                GridPane.getColumnIndex(node), GridPane.getRowIndex(node)));
            }
        }
        for (Node node : middleBoard.getChildren()) {
            if (node.getClass().equals(Tile.class)) {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> tryToMoveClickedPiece(((Tile) node).getzLevel(),
                                GridPane.getColumnIndex(node), GridPane.getRowIndex(node)));
            }
        }
        for (Node node : bottomBoard.getChildren()) {
            if (node.getClass().equals(Tile.class)) {
                node.addEventFilter(MouseEvent.MOUSE_CLICKED,
                        event -> tryToMoveClickedPiece(((Tile) node).getzLevel(),
                                GridPane.getColumnIndex(node), GridPane.getRowIndex(node)));
            }
        }
    }

    private void selectPiece(Piece piece) {
        boolean turn = piece.isWhite() ? whitePlayer.isMyTurn() : blackPlayer.isMyTurn();
        ArrayList<Piece> pieces = piece.isWhite() ? whitePlayer.getPieces() : blackPlayer.getPieces();

        if (turn) {
            for (Piece p : pieces) {
                colourTheme.unhighlightPiece(p, p.getzLevel());
            }

            clickedPiece = piece;
            colourTheme.highlightPiece(clickedPiece, clickedPiece.getzLevel());
        } else {
            tryToCapture(piece);
        }
    }

    private void tryToCapture(Piece prey) {
        final int predatorZLevel = clickedPiece.getzLevel();
        final int preyZLevel = prey.getzLevel();

        if (isAllowedToMove(clickedPiece.capturable(), predatorZLevel, preyZLevel,
                GridPane.getColumnIndex(prey), GridPane.getRowIndex(prey))) {
            // capturePrey();
        }
    }

    private boolean isAllowedToMove(final int[][] movable,
                            final int srcZLevel, final int destZLevel,
                            final int destColumnIndex, final int destRowIndex) {
        if (Math.abs(srcZLevel - destZLevel) > 1) {
            return false;
        }

        for (int[] cell : movable) {
            if (cell[0] == destColumnIndex && cell[1] == destRowIndex) {
                return true;
            }
        }
        return false;
    }

    private void switchTurn() {
        if (whitePlayer.isMyTurn()) {
            whitePlayer.finishMyTurn();
            blackPlayer.startMyTurn();
        } else {
            blackPlayer.finishMyTurn();
            whitePlayer.startMyTurn();
        }
    }

    private void tryToMoveClickedPiece(final int destZLevel, final int destColumnIndex, final int destRowIndex) {
        if (clickedPiece == null) {
            return;
        }

        boolean turn = clickedPiece.isWhite() ? whitePlayer.isMyTurn()
                : blackPlayer.isMyTurn();

        if (turn) {
            final int[][] movable = clickedPiece.movable();

            if (isAllowedToMove(movable, clickedPiece.getzLevel(), destZLevel, destColumnIndex, destRowIndex)) {
                    moveClickedPiece(destZLevel, destColumnIndex, destRowIndex);
                    switchTurn();
            }
        }

    }

    private void moveClickedPiece(final int destzLevel, final int destColumnIndex, final int destRowIndex) {
        clickedPiece.setzLevel(destzLevel);
        clickedPiece.setColumnIndex(destColumnIndex);
        clickedPiece.setRowIndex(destRowIndex);

        if (clickedPiece.getClass().equals(Pawn.class)
                || clickedPiece.getClass().equals(Rook.class)
                || clickedPiece.getClass().equals(King.class)) {
            clickedPiece.setNeverMoved(false);
        }

        colourTheme.unhighlightPiece(clickedPiece, clickedPiece.getzLevel());
        clickedPiece = null;

        refreshPiecesOnBoards();
    }

    private void refreshPiecesOnBoards() {
        topBoard.refreshPieces();
        middleBoard.refreshPieces();
        bottomBoard.refreshPieces();
    }

}
