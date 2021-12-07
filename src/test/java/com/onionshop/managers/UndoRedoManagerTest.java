package com.onionshop.managers;

import com.onionshop.controllers.KeyboardEventController;
import com.onionshop.controllers.ProjectStateController;
import com.onionshop.entities.DrawingState;
import com.onionshop.entities.Pixel;
import com.onionshop.entities.Project;
import com.onionshop.events.NewProjectEvent;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UndoRedoManagerTest {
    private final ProjectManager projectManager = new ProjectManager();
    private final ProjectStateController projectStateController = new ProjectStateController();
    private final KeyboardEventController keyboardEventController = new KeyboardEventController(projectStateController);

    /**
     * Create a temporary folder.
     */
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();


    Pixel pixel1 = new Pixel(new int[]{255, 255, 255});
    Pixel pixel2 = new Pixel(new int[]{0, 0, 0});


    /**
     * Set up a drawn canvas before each test.
     */
    @BeforeEach
    public void setUp() throws Exception {
        folder.create();
        NewProjectEvent newProjectEvent = new NewProjectEvent("testFile1", folder.getRoot().getPath(),
                2, 2);
        projectManager.newProject(newProjectEvent);
        Pixel[][] array1 = new Pixel[2][2];
        array1[0][0] = pixel2;
        array1[0][1] = pixel1;
        array1[1][0] = pixel1;
        array1[1][1] = pixel1;
        projectManager.updateDrawingCanvas(array1);
        Pixel[][] array2 = new Pixel[2][2];
        array2[0][0] = pixel2;
        array2[0][1] = pixel2;
        array2[1][0] = pixel1;
        array2[1][1] = pixel1;
        projectManager.updateDrawingCanvas(array2);
    }

    /**
     * Test undo once.
     */
//    @Test
//    public void testUndo() {
//        projectManager.undoDrawingState();
//        Pixel[][] expect = projectManager.getUndoRedoState().undoStack.peek().getState();
//        assertEquals(expect, projectManager.getCurrentProject().drawingCanvas);
//    }
//
//    /**
//     * Test undo twice.
//     */
//    @Test
//    public void testUndoTwice() {
//        projectManager.undoDrawingState();
//        projectManager.undoDrawingState();
//        Pixel[][] expect = projectManager.getUndoRedoState().undoStack.peek().getState();
//        assertEquals(expect, projectManager.getCurrentProject().drawingCanvas);
//    }

    /**
     * Test if the drawing state been push to redo stack after undo.
     */
    @Test
    public void pushRedo() {
        projectManager.undoDrawingState();
        assert projectManager.getUndoRedoState().redoStack.size() == 1;
    }

    /**
     * Test redo after undo. If this test and the pushRedo test both pass, it means the state indeed been push to redo
     * stack and the redo function also work.
     */
    @Test
    public void testRedo() {
        projectManager.undoDrawingState();
        projectManager.restoreDrawingState();
        assert projectManager.getUndoRedoState().redoStack.isEmpty();
    }

}
