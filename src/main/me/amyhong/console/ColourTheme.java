package me.amyhong.console;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import me.amyhong.board.Board;
import me.amyhong.board.Tile;
import me.amyhong.pieces.Piece;

import java.util.ArrayList;

public class ColourTheme {

    private static final Color pieceWhite[] = {Color.web("0xFFF")};
    private static final Color pieceBlack[] = {Color.web("0x000")};
    private static final Color pieceClicked[] = {Color.web("0x2793f4"), Color.web("0xff2d3a"), Color.web("0x999"), Color.web("0xff5f2e"), Color.web("0xbd1550")};

    private static final Color tileLight[] = {Color.web("0xFFF8E7"), Color.web("0xf4f5f9"), Color.web("0xfff"), Color.web("0xfcbe32"), Color.web("0xd9e1e8")};
    private static final Color tileDark[] = {Color.web("0x2F3138"), Color.web("0x252c41"), Color.web("0x141414"), Color.web("0x004e66"), Color.web("0x1c140d")};
    private static final Color tileHighlight[] = {Color.web("0xF99"), Color.web("0x22f9ef"), Color.web("0x883d94"), Color.web("0xe1eef6"), Color.web("0x99f19e")};

    private int themeNumbers[] = new int[3];

    public ColourTheme() {
        setColourTheme(4);
    }

    public ColourTheme(final int themeNumber) {
        setColourTheme(themeNumber);
    }

    public ColourTheme(final int themeNumberTop, final int themeNumberMiddle, final int themeNumberBottom) {
        themeNumbers[0] = themeNumberTop;
        themeNumbers[1] = themeNumberMiddle;
        themeNumbers[2] = themeNumberBottom;
    }

    /**
     * Sets colour theme number for every board.
     *
     * @param themeNumber theme number to set
     */
    public void setColourTheme(final int themeNumber) {
        for (int i = 0; i < themeNumbers.length; i++) {
            themeNumbers[i] = themeNumber;
        }
    }

    /**
     * Sets colour theme number for every board.
     *
     * @param themeNumber theme number to set
     */
    public void setColourTheme(final int themeNumber, final int verticalLevel) {
        themeNumbers[verticalLevel] = themeNumber;
    }

    /**
     * Returns normal-unhighlighted colour of the given object to paint.
     *
     * @param objectToPaint object to paint
     * @param white         true if the given object is white
     * @param verticalLevel vertical level of a chessboard where given object is located
     * @return normal-unhighlight colour of the given object to paint
     * @throws IllegalArgumentException if the given object is not piece or tile
     */
    private Color findUnhighlightColour(final Object objectToPaint,
                                        final boolean white, final int verticalLevel)
            throws IllegalArgumentException {
        final boolean piece = Piece.isPiece(objectToPaint);
        final boolean tile = objectToPaint.getClass().equals(Tile.class);
        final int themeNumber = themeNumbers[verticalLevel];

        if (!(piece || tile)) {
            throw new IllegalArgumentException("Given object to paint is not piece or tile.");
        }
        return white ? (piece ? pieceWhite[0] : tileLight[themeNumber])
                : (piece ? pieceBlack[0] : tileDark[themeNumber]);

    }

    /**
     * Returns highlighted colour of the given object to paint.
     *
     * @param objectToPaint object to paint
     * @param verticalLevel vertical level of a chessboard where given object is located
     * @return highlight colour of the given object to paint
     * @throws IllegalArgumentException if the given object is not piece or tile
     */
    private Color findHighlightColour(final Object objectToPaint, final int verticalLevel)
            throws IllegalArgumentException {
        final boolean piece = Piece.isPiece(objectToPaint);
        final boolean tile = objectToPaint.getClass().equals(Tile.class);
        final int themeNumber = themeNumbers[verticalLevel];

        if (!(piece || tile)) {
            throw new IllegalArgumentException("Given object to paint is not piece or tile.");
        }
        return piece ? pieceClicked[themeNumber] : tileHighlight[themeNumber];
    }

    /**
     * Returns true if successfully paints given object with normal-unhighlight colour.
     *
     * @param objectToPaint object to paint
     * @param verticalLevel vertical level of a chessboard where the given object is located
     * @return true if successfully paints given object with normal-unhighlight colour.
     *              If the given object to paint is not piece or tile, returns false.
     */
    public boolean unhighlight(Object objectToPaint, final int verticalLevel) {
        if (Piece.isPiece(objectToPaint)) {
            ((Piece) objectToPaint).setFill(
                    findUnhighlightColour(objectToPaint, ((Piece) objectToPaint).isWhite(), verticalLevel));
            return true;
        } else if (objectToPaint.getClass().equals(Tile.class)) {
            ((Tile) objectToPaint).setFill(
                    findUnhighlightColour(objectToPaint, ((Tile) objectToPaint).isWhite(), verticalLevel));
            return true;
        } else {
            return false;
        }
    }

    public void highlightPiece(Piece piece, final int verticalLevel) {
        piece.setHighlighted(true);
        piece.setFill(findHighlightColour(piece, verticalLevel));
    }

    public void unhighlightPiece(Piece piece, final int verticalLevel) {
        piece.setHighlighted(false);
        unhighlight(piece, verticalLevel);
    }

    public void unhighlightPieces(ArrayList<Piece> pieces) {
        for (Piece p : pieces) {
            p.setHighlighted(false);
            unhighlight(p, 0);
            unhighlight(p, 1);
            unhighlight(p, 2);
        }
    }

    public void highlightTile(Tile tile, final int verticalLevel) {
        tile.setHighlighted(true);

        if (tile.isHighlighted()) {
            tile.setFill(findHighlightColour(tile, verticalLevel));
        } else {
            unhighlight(tile, verticalLevel);
        }
    }

    public void unhighlightTiles(Tile tile, final int verticalLevel) {
        tile.setHighlighted(false);
        unhighlight(tile, verticalLevel);
    }

    public void unhighlightTiles(Board board, final int verticalLevel) {
        for (Node node : board.getChildren()) {
            if (node.getClass().equals(Tile.class)) {
                ((Tile) node).setHighlighted(false);
                unhighlight(node, verticalLevel);
            }
        }
    }

    /**
     * Paints a given vertical level's board by current theme number.
     *
     * @param board board to paint
     */
    public void paintByTheme(Board board) {
        final int verticalLevel = board.getVerticalLevel();

        for (Node node : board.getChildren()) {
            unhighlight(node, verticalLevel);
        }
    }

    /**
     * Paints a given vertical level's board by current theme number.
     *
     * @param boards boards to paint
     */
    public void paintByTheme(final Board[] boards) {
        for (int i = 0; i < boards.length; i++) {
            for (Node node : boards[i].getChildren()) {
                unhighlight(node, i);
            }
        }
    }

}
