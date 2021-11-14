package com.onionshop.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColourPaletteTest {
    ColourPalette colourPalette;
    Colour red;
    Colour green;

    @BeforeEach
    public void setUp() {
        red = new Colour("red", new int[]{255, 0, 0});
        green = new Colour("green", new int[]{0, 255, 0});

        ArrayList<Colour> colours = new ArrayList<Colour>(List.of(new Colour[]{red, green}));
        colourPalette = new ColourPalette(colours);
    }

    @Test
    public void testAddColour() {
        Colour blue = new Colour("blue", new int[]{0, 0, 255});
        colourPalette.addColour(blue);
        assert (colourPalette.getColours().contains(blue));
        for (Colour c : colourPalette.getColours()) {
            if (c == blue) {
                assertEquals(c.getName(), blue.getName());
                assertEquals(c.RGB, blue.RGB);
            }
        }
    }

    @Test
    public void testRemoveColourByName() {
        colourPalette.removeColour("red");
        assertEquals(colourPalette.size(), 1);
        assertEquals(colourPalette.getColour(0).getName(), "green");
    }

    @Test
    public void testRemoveColourByColour() {
        colourPalette.removeColour(red);
        assertEquals(colourPalette.size(), 1);
        assertEquals(colourPalette.getColour(0).getName(), "green");
    }

    @Test void testRemoveColourByIndex() {
        ArrayList<Colour> colours = colourPalette.getColours();
        colours.remove(0);
        colourPalette.removeColour(0);
        assertEquals(colourPalette.getColours(), colours);

    }

    @Test
    void getColourByName() {
        assertEquals(colourPalette.getColour("green"), green);
    }
}
