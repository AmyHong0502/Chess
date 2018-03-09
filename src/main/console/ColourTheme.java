package main.console;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import main.board.Board;
import main.board.Tile;
import main.pieces.*;

public class ColourTheme {

    private static final Color pieceWhite1 = Color.web("0xFFF");
    private static final Color pieceBlack1 = Color.web("0x000");
    private static final Color pieceClicked1 = Color.web("0x3885CA");
    private static final Color pieceWhite2 = Color.web("0xFFF");
    private static final Color pieceBlack2 = Color.web("0x000");
    private static final Color pieceClicked2 = Color.web("0xf1404b");
    private static final Color pieceWhite3 = Color.web("0xFFF");
    private static final Color pieceBlack3 = Color.web("0x000");
    private static final Color pieceClicked3 = Color.web("0x3885CA");

    private static final Color tileLight1 = Color.web("0xFFF8E7");
    private static final Color tileDark1 = Color.web("0x2F3138");
    private static final Color tileHighlight1 = Color.web("0xF99");
    private static final Color tileLight2 = Color.web("0xf4f5f9");
    private static final Color tileDark2 = Color.web("0x252c41");
    private static final Color tileHighlight2 = Color.web("0xF99");
    private static final Color tileLight3 = Color.web("0xDDD");
    private static final Color tileDark3 = Color.web("0x333");
    private static final Color tileHighlight3 = Color.web("0xF99");

    private int colourThemeNumber;

    public ColourTheme() {
        this.colourThemeNumber = 1;
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

        switch (colourThemeNumber) {
            case 1:
                return white ? (piece ? pieceWhite1 : tileLight1) : (piece ? pieceBlack1 : tileDark1);
            case 2:
                return white ? (piece ? pieceWhite2 : tileLight2) : (piece ? pieceBlack2 : tileDark2);
            case 3:
            default:
                return white ? (piece ? pieceWhite3 : tileLight3) : (piece ? pieceBlack3 : tileDark3);
        }
    }

    private Color findHighlightColour(Object objectToPaint) throws IllegalArgumentException {
        final boolean piece = isPiece(objectToPaint);
        final boolean tile = objectToPaint.getClass().equals(Tile.class);

        if (!(piece || tile)) {
            throw new IllegalArgumentException("Given object to paint is not piece or tile.");
        }

        switch (colourThemeNumber) {
            case 1:
                return piece ? pieceClicked1 : tileHighlight1;
            case 2:
                return piece ? pieceClicked2 : tileHighlight2;
            case 3:
            default:
                return piece ? pieceClicked3 : tileHighlight3;
        }
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

    public void paintByTheme(Board board) {
        for (Node node : board.getChildren()) {
            paintDefault(node);
        }
    }
}
