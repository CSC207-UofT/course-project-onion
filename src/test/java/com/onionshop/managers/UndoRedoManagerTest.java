package com.onionshop.managers;

import com.onionshop.entities.Pen;
import com.onionshop.entities.Project;
import com.onionshop.managers.DrawingManager;
import com.onionshop.managers.UndoRedoManager;
import com.onionshop.controllers.NewProjectController;
import org.junit.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.File;
import java.io.IOException;

public class UndoRedoManagerTest {
    Project p;
    private final DrawingManager Drawing = new DrawingManager();
    private final UndoRedoManager UndoRedo = new UndoRedoManager();


    @BeforeEach
    public void setUp() throws Exception {
        p = new Project("test", 2, 2);
        Drawing.updateCanvasAfterStroke(1,1);
        Drawing.updateCanvasAfterStroke(1,2);
    }

    @Test
    public void testUndo() {
        UndoRedo.undo();
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
}
