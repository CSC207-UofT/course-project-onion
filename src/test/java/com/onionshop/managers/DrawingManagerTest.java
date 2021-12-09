package com.onionshop.managers;

import com.onionshop.entities.*;
import com.onionshop.events.NewProjectEvent;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DrawingManagerTest {
    /**
     * This is the test suite for the DrawingManager class.
     */
    static String savePath;
    static DrawingManager drawingManager;

    @TempDir
    static Path tempDir;

    /**
     * This function creates a temporary file to set up the project before each test. It also creates a new project and
     * DrawingManager with a clear canvas and color palette.
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
        List<Colour> colours = new ArrayList<Colour>();
        ColourPalette colourPalette = new ColourPalette(colours);
        ProjectManager.getInstance().getCurrentProject().setColourPalette(colourPalette);
        drawingManager = new DrawingManager();
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
     * This functions tests whether the correct pixels are edited when x, y coordinates are handed to the drawingManger's
     * updateCanvasAfterStroke function.
     */
    @Test
    public void testUpdateCanvasAfterStroke() {
        int brushSize = 10;
        int[] rgb = new int[]{123, 230, 3, 255};
        Colour testColour = new Colour("Test", rgb);
        ToolStateManager.getInstance().setCurrentToolState(new Pen("round", brushSize));
        ToolStateManager.getInstance().setCurrentColourState(testColour);
        int x = 39;
        int y = 100;
        drawingManager.updateCanvasAfterStroke(x, y);
        Project currentProject = ProjectManager.getInstance().getCurrentProject();
        for (int i = x - brushSize; i < x + brushSize; i++) {
            for (int j = y - brushSize; j < y + brushSize; j++) {
                assertArrayEquals(currentProject.getLayers().get(0).getPixelByCoord(i, j).getRGB(), rgb);
            }
        }
    }

    /**
     * This function tests whether the toolStateManager is correctly updated with a new colour by the drawingManager's
     * updateSelectedColour function.
     */
    @Test
    public void testUpdateSelectedColour() {
        int[] rgb = new int[]{244, 0, 130, 255};
        Colour testColour = new Colour("Test", rgb);
        drawingManager.updateSelectedColour(testColour);
        int[] currentToolRgb = ToolStateManager.getInstance().getCurrentColourState().getRGB();
        assertArrayEquals(currentToolRgb, rgb);
    }

    /**
     * This function tests whether colours are correctly added to the colour palette by the addColourToPalette function
     */
    @Test
    public void testAddColourToPalette() {
        String testColourHex = "#062488";
        int[] rgb = new int[]{6, 36, 136, 255};
        Colour testColour = new Colour(testColourHex, rgb);
        drawingManager.addColourToPalette(testColour);
        int[] currentPaletteRGB = ProjectManager.getInstance().getCurrentProject().getColourPalette().
                getColour(0).getRGB();
        assertArrayEquals(currentPaletteRGB, rgb);

    }

    /**
     * This function tests whether the correct colour is returned by the selectColourFromPalette function if the colour
     * is in the palette.
     */
    @Test
    public void testSelectColourFromPaletteColourFound() {
        String testColourHex = "#b5a1a1";
        int[] rgb = new int[]{181, 161, 161, 255};
        Colour testColour = new Colour(testColourHex, rgb);
        String testColourHex2 = "#21424a";
        int[] rgb2 = new int[]{33, 66, 74, 255};
        Colour testColour2 = new Colour(testColourHex2, rgb2);
        String testColourHex3 = "#bce0c3";
        int[] rgb3 = new int[]{188, 224, 195, 255};
        Colour testColour3 = new Colour(testColourHex3, rgb3);

        drawingManager.addColourToPalette(testColour);
        drawingManager.addColourToPalette(testColour2);
        drawingManager.addColourToPalette(testColour3);

        int[] currentPaletteRGB = drawingManager.selectColourFromPalette(testColourHex2);
        assertArrayEquals(currentPaletteRGB, rgb2);
    }

    /**
     * This function tests if Black is correctly returned when a String for a colour not in the palette is passed in to
     * the selectColourFromPalette function.
     */
    @Test
    public void testSelectColourFromPaletteColourNotFound() {
        String testColourHex = "#b5a1a1";
        int[] rgb = new int[]{181, 161, 161, 255};
        Colour testColour = new Colour(testColourHex, rgb);
        String testColourHex2 = "#21424a";
        int[] rgb2 = new int[]{33, 66, 74, 255};
        Colour testColour2 = new Colour(testColourHex2, rgb2);
        String testColourHex3 = "#bce0c3";
        int[] rgb3 = new int[]{188, 224, 195, 255};
        Colour testColour3 = new Colour(testColourHex3, rgb3);

        drawingManager.addColourToPalette(testColour);
        drawingManager.addColourToPalette(testColour2);
        drawingManager.addColourToPalette(testColour3);

        int[] currentPaletteRGB = drawingManager.selectColourFromPalette("#ba2010");
        assertArrayEquals(currentPaletteRGB, new int[]{0, 0, 0, 255});
    }

    /**
     * This function tests if colours are correctly removed from the palette by the removeColourFromPalette function
     */
    @Test
    public void testRemoveColourFromPaletteColourFound() {
        String testColourHex = "#b5a1a1";
        int[] rgb = new int[]{181, 161, 161};
        Colour testColour = new Colour(testColourHex, rgb);
        String testColourHex2 = "#21424a";
        int[] rgb2 = new int[]{33, 66, 74};
        Colour testColour2 = new Colour(testColourHex2, rgb2);
        String testColourHex3 = "#bce0c3";
        int[] rgb3 = new int[]{188, 224, 195};
        Colour testColour3 = new Colour(testColourHex3, rgb3);

        drawingManager.addColourToPalette(testColour);
        drawingManager.addColourToPalette(testColour2);
        drawingManager.addColourToPalette(testColour3);

        drawingManager.removeColourFromPalette(testColourHex);
        int[] currentPaletteRGB = drawingManager.selectColourFromPalette(testColourHex);
        assertArrayEquals(currentPaletteRGB, new int[]{0, 0, 0, 255});
    }

    /**
     * This function tests if the removeColourFromPalette function correctly handles the case where a colour not in the
     * palette is passed in to the function. It does this by ensuring all colours added to the palette previously are
     * still in the palette after the function is run.
     */
    @Test
    public void testRemoveColourFromPaletteColourNotFound() {
        String testColourHex = "#b5a1a1";
        int[] rgb = new int[]{181, 161, 161, 255};
        Colour testColour = new Colour(testColourHex, rgb);
        String testColourHex2 = "#21424a";
        int[] rgb2 = new int[]{33, 66, 74, 255};
        Colour testColour2 = new Colour(testColourHex2, rgb2);
        String testColourHex3 = "#bce0c3";
        int[] rgb3 = new int[]{188, 224, 195, 255};
        Colour testColour3 = new Colour(testColourHex3, rgb3);

        drawingManager.addColourToPalette(testColour);
        drawingManager.addColourToPalette(testColour2);
        drawingManager.addColourToPalette(testColour3);

        drawingManager.removeColourFromPalette("#1024ac");
        int[] currentPaletteRGB1 = drawingManager.selectColourFromPalette(testColourHex);
        int[] currentPaletteRGB2 = drawingManager.selectColourFromPalette(testColourHex2);
        int[] currentPaletteRGB3 = drawingManager.selectColourFromPalette(testColourHex3);
        assertArrayEquals(currentPaletteRGB1, rgb);
        assertArrayEquals(currentPaletteRGB2, rgb2);
        assertArrayEquals(currentPaletteRGB3, rgb3);
    }


}
