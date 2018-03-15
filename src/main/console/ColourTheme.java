package main.console;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import main.board.Board;
import main.board.Tile;
import main.pieces.*;

public class ColourTheme {

    private static final Color pieceWhite[] = {Color.web("0xFFF")};
    private static final Color pieceBlack[] = {Color.web("0x000")};
    private static final Color pieceClicked[] = {Color.web("0x2793f4"), Color.web("0xff2d3a"), Color.web("0x45d9fd"), Color.web("0xff5f2e"), Color.web("0xbd1550")};

    private static final Color tileLight[] = {Color.web("0xFFF8E7"), Color.web("0xf4f5f9"), Color.web("0xf4f4f4"), Color.web("0xfcbe32"), Color.web("0xd9e1e8")};
    private static final Color tileDark[] = {Color.web("0x2F3138"), Color.web("0x252c41"), Color.web("0x08182b"), Color.web("0x004e66"), Color.web("0x1c140d")};
    private static final Color tileHighlight[] = {Color.web("0xF99"), Color.web("0x22f9ef"), Color.web("0xee2560"), Color.web("0xe1eef6"), Color.web("0x99f19e")};

    private int themeNumberTop;
    private int themeNumberMiddle;
    private int themeNumberBottom;

    public ColourTheme() {
        setColourTheme(4);
    }

    public ColourTheme(final int themeNumber) {
        setColourTheme(themeNumber);
    }

    public ColourTheme(final int themeNumberTop, final int themeNumberMiddle, final int themeNumberBottom) {
        this.themeNumberTop = themeNumberTop;
        this.themeNumberMiddle = themeNumberMiddle;
        this.themeNumberBottom = themeNumberBottom;
    }

    public void setColourTheme(final int themeNumber) {
        themeNumberTop = themeNumber;
        themeNumberMiddle = themeNumber;
        themeNumberBottom = themeNumber;
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

    private Color findDefaultColour(final Object objectToPaint, final boolean white, final int zlevel) throws IllegalArgumentException {
        final boolean piece = isPiece(objectToPaint);
        final boolean tile = objectToPaint.getClass().equals(Tile.class);
        final int themeNumber = zlevel == 0 ? themeNumberTop : zlevel == 1 ? themeNumberMiddle : themeNumberBottom;

        if (!(piece || tile)) {
            throw new IllegalArgumentException("Given object to paint is not piece or tile.");
        }
        return white ? (piece ? pieceWhite[0] : tileLight[themeNumber])
                    : (piece ? pieceBlack[0] : tileDark[themeNumber]);

    }

    private Color findHighlightColour(final Object objectToPaint, final int zlevel) throws IllegalArgumentException {
        final boolean piece = isPiece(objectToPaint);
        final boolean tile = objectToPaint.getClass().equals(Tile.class);
        final int themeNumber = zlevel == 0 ? themeNumberTop : zlevel == 1 ? themeNumberMiddle : themeNumberBottom;

        if (!(piece || tile)) {
            throw new IllegalArgumentException("Given object to paint is not piece or tile.");
        }
        return piece ? pieceClicked[themeNumber] : tileHighlight[themeNumber];
    }

    public void paintDefault(Object objectToPaint, final int zlevel) {
        if (isPiece(objectToPaint)) {
            ((Piece) objectToPaint).setFill(findDefaultColour(objectToPaint,((Piece) objectToPaint).isWhite(), zlevel));
        } else if (objectToPaint.getClass().equals(Tile.class)) {
            ((Tile) objectToPaint).setFill(findDefaultColour(objectToPaint, ((Tile) objectToPaint).isWhite(), zlevel));
        } else {
            throw new IllegalArgumentException("Given object to paint is not piece or tile.");
        }
    }

    public void highlightPiece(Piece piece, final int zlevel) {
        piece.setHighlighted(true);
        piece.setFill(findHighlightColour(piece, zlevel));
    }

    public void unhighlightPiece(Piece piece, final int zlevel) {
        piece.setHighlighted(false);
        paintDefault(piece, zlevel);
    }

    public void highlightTile(Tile tile, final int zlevel) {
        tile.setHighlighted(true);

        if (tile.isHighlighted()) {
            tile.setFill(findHighlightColour(tile, zlevel));
        } else {
            paintDefault(tile, zlevel);
        }
    }

    public void unhighlightTiles(Tile tile, final int zlevel) {
        tile.setHighlighted(false);
        paintDefault(tile, zlevel);
    }

    public void unhighlightTiles(Board board, final int zlevel) {
        for (Node node : board.getChildren()) {
            if (node.getClass().equals(Tile.class)) {
                ((Tile) node).setHighlighted(false);
                paintDefault(node, zlevel);
            }
        }
    }

    public void paintByTheme(Board board, final int zlevel) {
        for (Node node : board.getChildren()) {
            paintDefault(node, zlevel);
        }
    }
}
