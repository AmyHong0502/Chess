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

    private int colourThemeNumber;

    public ColourTheme() {
        this.colourThemeNumber = 4;
    }

    public ColourTheme(final int colourThemeNumber) {
        this.colourThemeNumber = colourThemeNumber;
    }

    public void setColourTheme(final int colourThemeNumber) {
        this.colourThemeNumber = colourThemeNumber;
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

    private Color findDefaultColour(Object objectToPaint, boolean white) throws IllegalArgumentException {
        final boolean piece = isPiece(objectToPaint);
        final boolean tile = objectToPaint.getClass().equals(Tile.class);

        if (!(piece || tile)) {
            throw new IllegalArgumentException("Given object to paint is not piece or tile.");
        }
        return white ? (piece ? pieceWhite[0] : tileLight[colourThemeNumber])
                    : (piece ? pieceBlack[0] : tileDark[colourThemeNumber]);

    }

    private Color findHighlightColour(Object objectToPaint) throws IllegalArgumentException {
        final boolean piece = isPiece(objectToPaint);
        final boolean tile = objectToPaint.getClass().equals(Tile.class);

        if (!(piece || tile)) {
            throw new IllegalArgumentException("Given object to paint is not piece or tile.");
        }
        return piece ? pieceClicked[colourThemeNumber] : tileHighlight[colourThemeNumber];
    }

    public void paintDefault(Object objectToPaint) {
        if (isPiece(objectToPaint)) {
            ((Piece) objectToPaint).setFill(findDefaultColour(objectToPaint,((Piece) objectToPaint).isWhite()));
        } else if (objectToPaint.getClass().equals(Tile.class)) {
            ((Tile) objectToPaint).setFill(findDefaultColour(objectToPaint, ((Tile) objectToPaint).isWhite()));
        } else {
            System.out.println(objectToPaint.getClass() + "\n\n\n");
            throw new IllegalArgumentException("Given object to paint is not piece or tile.");
        }
    }

    public void highlightPiece(Piece piece) {
        boolean highlighted = piece.isHighlighted();
        piece.setHighlighted(!highlighted);

        if (piece.isHighlighted()) {
            piece.setFill(findHighlightColour(piece));
        } else {
            paintDefault(piece);
        }
    }

    public void highlightTile(Tile tile) {
        tile.setHighlighted(true);

        if (tile.isHighlighted()) {
            tile.setFill(findHighlightColour(tile));
        } else {
            paintDefault(tile);
        }
    }

    public void unhighlightTiles(Tile tile) {
        tile.setHighlighted(false);
        paintDefault(tile);
    }

    public void unhighlightTiles(Board board) {
        for (Node node : board.getChildren()) {
            if (node.getClass().equals(Tile.class)) {
                ((Tile) node).setHighlighted(false);
                paintDefault(node);
            }
        }
    }

    public void paintByTheme(Board board) {
        for (Node node : board.getChildren()) {
            paintDefault(node);
        }
    }
}
