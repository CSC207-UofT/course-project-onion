package com.onionshop.entities;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProjectTest {
    Project p;

    @BeforeEach
    public void setUp() throws Exception {
        p = new Project("test", 2, 2);
    }

    @Test
    public void serializeTest() {
        String[] lines = p.serialize();
        String[] expected = {
                "[dimensions]",
                "width:2",
                "height:2",
                "[saved colours]",
                "[pixels]",
                "255,255,255",
                "255,255,255",
                "255,255,255",
                "255,255,255",
                "[end]"};
        assertArrayEquals(lines, expected);
    }

}
