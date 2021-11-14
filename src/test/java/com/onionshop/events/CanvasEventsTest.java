package com.onionshop.events;

import com.onionshop.controllers.Tools;
import com.onionshop.entities.*;
import com.onionshop.managers.DrawingManager;
import com.onionshop.managers.ProjectManager;
import com.onionshop.managers.ToolStateManager;
import javafx.scene.paint.Color;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class CanvasEventsTest {
    /**
     * This is the test suite for the CanvasEvents class
     */
    static String savePath;
    static CanvasEvents canvasEvents;
    static DrawingManager drawingManager;

    @TempDir
    static Path tempDir;

    /**
     * This function creates a temporary file to set up the project before each test. It also creates a new project,
     * CanvasEvents, and DrawingManager class with a clear canvas and color palette.
     *
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        Path tempFilePath = Files.createFile(tempDir.resolve("testFile1"));
        savePath = tempFilePath.toString();
        NewProjectEvent newProjectEvent = new NewProjectEvent("TestProject", savePath, 1000,
                500);
        ProjectManager.getInstance().newProject(newProjectEvent);
        drawingManager = new DrawingManager();
        canvasEvents = new CanvasEvents(drawingManager);
    }

    /**
     * This deletes the temporary file for the next test
     *
     * @throws Exception
     */
    @AfterEach
    public void tearDown() throws Exception {
        Files.deleteIfExists(tempDir.resolve("testFile1"));
    }

    /**
     * This tests if passed in Colors are correctly processed by the testProcessSelectedColour function
     */
    @Test
    public void testProcessSelectedColour() {
        int conversionValue = 255;
        Color testColor = new Color(1.0, .7, .25, 1.0);
        int[] colourArray = new int[]{(int)(testColor.getRed()*conversionValue ),
                (int)(testColor.getGreen()*conversionValue ), (int)(testColor.getBlue()*conversionValue )};
        canvasEvents.processSelectedColour(testColor);
        assertArrayEquals(ToolStateManager.getInstance().getCurrentColourState().getRGB(), colourArray);
    }

    /**
     * This method tests that the correct Color hex value is returned and that the colour is correctly added to the
     * backend palette
     */
    @Test
    public void testProcessColourToAddToPalette() {
        int conversionValue = 255;
        Color testColor = new Color(0.811, 0.188, 0.188, 1.0);
        int[] colourArray = new int[]{(int)(testColor.getRed()*conversionValue ),
                (int)(testColor.getGreen()*conversionValue ), (int)(testColor.getBlue()*conversionValue )};

        String hexColorValue = String.format("#%02x%02x%02x", colourArray[0], colourArray[1], colourArray[2]);
        String testColorString = canvasEvents.processColourToAddToPalette(testColor);

        assertEquals(testColorString, hexColorValue);
        assertArrayEquals(ProjectManager.getInstance().getCurrentProject().getColourPalette()
                .getColour(0).getRGB(), colourArray);
    }

    /**
     * This method tests that each tools is correctly selected by the setTool method.
     */
    @Test
    public void testSetTool() {
        canvasEvents.setTool(Tools.LINE, 20);
        assert(ToolStateManager.getInstance().getCurrentToolState() instanceof Line);
        canvasEvents.setTool(Tools.PEN, 43);
        assert(ToolStateManager.getInstance().getCurrentToolState() instanceof Pen);
        canvasEvents.setTool(Tools.RECTANGLE, 34);
        assert(ToolStateManager.getInstance().getCurrentToolState() instanceof Rectangle);
        canvasEvents.setTool(Tools.ERASER, 23);
        assert(ToolStateManager.getInstance().getCurrentToolState() instanceof Eraser);
        canvasEvents.setTool(Tools.CIRCLE, 23);
        assert(ToolStateManager.getInstance().getCurrentToolState() instanceof Circle);
    }
}