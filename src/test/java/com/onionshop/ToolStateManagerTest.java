package com.onionshop;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
        manager.updateCurrentToolState("square", 300);
        assertEquals("square", ((Pen) manager.getCurrentToolState()).getBrushShape());
        assertEquals(300, ((Pen) manager.getCurrentToolState()).getBrushSize());
    }

    @Test
    public void testUpdateCurrentToolStateOnlyBrushSize() {
        manager.updateCurrentToolState(23);
        assertEquals(23, ((Pen) manager.getCurrentToolState()).getBrushSize());
    }

    @Test
    public void testUpdateCurrentToolStateOnlyBrushShape() {
        manager.updateCurrentToolState("round");
        assertEquals("round", ((Pen) manager.getCurrentToolState()).getBrushShape());
    }

    @Test
    public void testCurrentColourState() {
        int[] c_value = new int[] {1, 2, 3};
        assertArrayEquals(c_value, manager.getCurrentColourState().getRGB());
    }

    @Test
    public void testUpdateCurrentColourState() {
        int[] new_c = {3, 6, 7};
        manager.updateCurrentColourState(new_c);
        assert(manager.getCurrentToolState() instanceof Pen);
        assertArrayEquals(new int[]{3, 6, 7}, manager.getCurrentColourState().getRGB());
    }
}