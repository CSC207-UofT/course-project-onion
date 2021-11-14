package com.onionshop.managers;

import com.onionshop.entities.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UndoRedoManagerTest {
    Project p;
    private final DrawingManager Drawing = new DrawingManager();
    private final UndoRedoManager UndoRedo = new UndoRedoManager();
    private final ProjectManager projectManager = new ProjectManager();

    /**
     * Set up a drawn canvas before each test.
     */
    @BeforeEach
    public void setUp() {
        p = new Project("test", 2, 2);
        Drawing.updateCanvasAfterStroke(1, 1);
        Drawing.updateCanvasAfterStroke(1, 2);
    }

    @Test
    public void testUndo() {
        projectManager.undoDrawingState();
        String[] project = p.serialize();
        String[] expected = {
                "[dimensions]",
                "width:2",
                "height:2",
                "[saved colours]",
                "black:0,0,0",
                "[pixels]",
                "0,0,0",
                "255,255,255",
                "255,255,255",
                "255,255,255",
                "[end]"};
        assertEquals(project, expected);
    }

    @Test
    public void testUndoTwice() {
        projectManager.undoDrawingState();
        projectManager.undoDrawingState();
        String[] project = p.serialize();
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
        assertEquals(project, expected);
    }

    @Test
    public void testRedo() {
        projectManager.undoDrawingState();
        projectManager.restoreDrawingState();
        String[] project = p.serialize();
        String[] expected = {
                "[dimensions]",
                "width:2",
                "height:2",
                "[saved colours]",
                "black:0,0,0",
                "[pixels]",
                "0,0,0",
                "0,0,0",
                "255,255,255",
                "255,255,255",
                "[end]"};
        assertEquals(project, expected);
    }

}
