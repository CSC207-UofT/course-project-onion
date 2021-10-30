package com.onionshop.entities;
import org.junit.jupiter.api.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProjectTest {
    Project p;

    @BeforeEach
    public void setUp() throws Exception {
        p = new Project("test", 2, 2);
    }

    @Test
    public void testAddColour() {
        Colour newColour = new Colour("very blue", new int[]{0, 0, 255});
        p.addColour(newColour);
        assert (Arrays.asList(p.colourPalette).contains(newColour));
        for (Colour c : p.colourPalette) {
            if (c == newColour) {
                assertEquals(c.name, newColour.name);
                assertEquals(c.RGB, newColour.RGB);
            }
        }
    }

    @Test
    public void serializeTest() {
        String[] lines = p.serialize();
        String[] expected = {
                "[dimensions]",
                "width:2",
                "height:2",
                "[saved colours]",
                "black:0,0,0",
                "[pixels]",
                "255,255,255",
                "255,255,255",
                "255,255,255",
                "255,255,255",
                "[end]"};
        assertArrayEquals(lines, expected);
    }

}
