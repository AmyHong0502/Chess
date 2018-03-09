package main.console;

import javafx.scene.paint.Color;
import main.pieces.Piece;

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

    private static int colourThemeNumber;

    public ColourTheme() {
        this.colourThemeNumber = 1;
    }

    public ColourTheme(final int colourThemeNumber) {
        this.colourThemeNumber = colourThemeNumber;
    }

    private static Color pickHighlightedPieceColour() {
        switch (colourThemeNumber) {
            case 1:
                return pieceClicked1;
            case 2:
                return pieceClicked2;
            case 3:
            default:
                return pieceClicked3;
        }
    }

    public static void paintPiece(final Piece piece) {
        final Color colour;
        final boolean white = piece.isWhite();

        switch (colourThemeNumber) {
            case 1:
                colour = white ? pieceWhite1 : pieceBlack1;
                break;
            case 2:
                colour = white ? pieceWhite2 : pieceBlack2;
                break;
            case 3:
            default:
                colour = white ? pieceWhite3 : pieceBlack3;
                break;
        }
        piece.setFill(colour);
    }

    public static void highlightPiece(Piece piece) {
        boolean highlighted = piece.isHighlighted();
        piece.setHighlighted(!highlighted);

        Color highlightedColour = pickHighlightedPieceColour();

        if (piece.isHighlighted()) {
            piece.setFill(highlightedColour);
        } else {
            paintPiece(piece);
        }
    }

}
