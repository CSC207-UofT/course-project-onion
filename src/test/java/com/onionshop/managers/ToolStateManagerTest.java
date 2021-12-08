package com.onionshop.managers;

import com.onionshop.entities.Colour;
import com.onionshop.entities.Pen;
import com.onionshop.entities.Tool;
import com.onionshop.managers.ToolStateManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ToolStateManagerTest {
    /**
     * This class is a junit test class that test specifically the ToolStateManager.
     */
    Tool tool;
    Colour colour;
    ToolStateManager manager;

    /**
     * The Test set up for before each test, the current tool is Pen for now.
     *
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        int[] c = {1, 2, 3};
        tool = new Pen("round", 1);
        colour = new Colour("red", c);
        manager = new ToolStateManager(tool, colour);
    }

    /**
     * This test tests the method updateCurrentToolState which updates the pen class' brush shape and brush size.
     */
    @Test
    public void testUpdateCurrentToolState() {
        manager.updateCurrentToolState("square", 300);
        assertEquals("square", ((Pen) manager.getCurrentToolState()).getBrushShape());
        assertEquals(300, ((Pen) manager.getCurrentToolState()).getBrushSize());
    }

    /**
     * This test tests the constructor method updateCurrentToolState which only updates the pen class' brush size.
     */
    @Test
    public void testUpdateCurrentToolStateOnlyBrushSize() {
        manager.updateCurrentToolState(23);
        assertEquals(23, ((Pen) manager.getCurrentToolState()).getBrushSize());
    }

    /**
     * This test tests the constructor method updateCurrentToolState which only updates the pen class' brush shape.
     */
    @Test
    public void testUpdateCurrentToolStateOnlyBrushShape() {
        manager.updateCurrentToolState("round");
        assertEquals("round", ((Pen) manager.getCurrentToolState()).getBrushShape());
    }

    /**
     * This test tests the method updateCurrentColourState which updates the current RBG colour to a different one.
     */
    @Test
    public void testUpdateCurrentColourState() {
        int[] new_c = {3, 6, 7, 255};
        manager.updateCurrentColourState(new_c);
        assert(manager.getCurrentToolState() instanceof Pen);
        assertArrayEquals(new_c, manager.getCurrentColourState().getRGB());
    }
}