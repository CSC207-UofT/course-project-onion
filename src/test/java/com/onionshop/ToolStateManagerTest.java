package com.onionshop;

import org.junit.jupiter.api.*;

import static org.junit.Assert.*;

public class ToolStateManagerTest {
    Tool tool;
    Colour colour;
    ToolStateManager manager;

    @BeforeEach
    public void setUp() throws Exception {
        int[] c = {1, 2, 3};
        tool = new Pen("round", 1);
        colour = new Colour("red", c);
        manager = new ToolStateManager(tool, colour);
    }

    @Test
    public void testUpdateCurrentToolState() {
        // dummy assertion, should definitely evaluate as true
        assert(manager.getCurrentToolState() instanceof Brush);
    }


}