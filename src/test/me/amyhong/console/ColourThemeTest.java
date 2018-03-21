package me.amyhong.console;

import static org.junit.Assert.assertEquals;

import me.amyhong.board.Tile;
import me.amyhong.pieces.Rook;
import me.amyhong.saveLoad.SaveLoad;
import org.junit.Before;
import org.junit.Test;

public class ColourThemeTest {
    private ColourTheme colourTheme;

    @Before
    public void setUp() {
        colourTheme = new ColourTheme();
    }

    @Test
    public void testUnhighlightWrongObjectLv0() {
        boolean expected = false;
        boolean actual = colourTheme.unhighlight(new SaveLoad(), 0);
        assertEquals("Unhighlighting a SaveLoad object", expected, actual);
    }

    @Test
    public void testUnhighlightTileObjectLv0() {
        boolean expected = true;
        boolean actual = colourTheme.unhighlight(new Tile(0, 30, false), 0);
        assertEquals("Unhighlighting a Tile object", expected, actual);
    }

    @Test
    public void testUnhighlightPieceObjectLv0() {
        boolean expected = true;
        boolean actual = colourTheme.unhighlight(new Rook(true, 0, 0, 0, false), 0);
        assertEquals("Unhighlighting a Piece object", expected, actual);
    }

    @Test
    public void testUnhighlightWrongObjectLv1() {
        boolean expected = false;
        boolean actual = colourTheme.unhighlight("", 1);
        assertEquals("Unhighlighting a String object", expected, actual);
    }

    @Test
    public void testUnhighlightTileObjectLv1() {
        boolean expected = true;
        boolean actual = colourTheme.unhighlight(new Tile(1, 42, false), 1);
        assertEquals("Unhighlighting a Tile object", expected, actual);
    }

    @Test
    public void testUnhighlightPieceObjectLv1() {
        boolean expected = true;
        boolean actual = colourTheme.unhighlight(new Rook(false, 0, 0, 1, true), 1);
        assertEquals("Unhighlighting a Piece object", expected, actual);
    }

    @Test
    public void testUnhighlightWrongObjectLv2() {
        boolean expected = false;
        boolean actual = colourTheme.unhighlight(12345, 2);
        assertEquals("Unhighlighting an int", expected, actual);
    }

    @Test
    public void testUnhighlightTileObjectLv2() {
        boolean expected = true;
        boolean actual = colourTheme.unhighlight(new Tile(2, 1024, true), 2);
        assertEquals("Unhighlighting a Tile object", expected, actual);
    }

    @Test
    public void testUnhighlightPieceObjectLv2() {
        boolean expected = true;
        boolean actual = colourTheme.unhighlight(new Rook(false, 0, 0, 2, false), 2);
        assertEquals("Unhighlighting a Piece object", expected, actual);
    }
}
