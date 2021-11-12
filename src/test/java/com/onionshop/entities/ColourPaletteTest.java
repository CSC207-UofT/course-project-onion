package com.onionshop.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColourPaletteTest {
    ColourPalette colourPalette;

    @BeforeEach
    public void setUp() throws Exception {
        colourPalette = new ColourPalette(new ArrayList<Colour>());
    }

    @Test
    public void testAddColour() {
        Colour newColour = new Colour("very blue", new int[]{0, 0, 255});
        colourPalette.addColour(newColour);
        assert (colourPalette.getColours().contains(newColour));
        for (Colour c : colourPalette.getColours()) {
            if (c == newColour) {
                assertEquals(c.getName(), newColour.getName());
                assertEquals(c.RGB, newColour.RGB);
            }
        }
    }
}
