package com.onionshop.managers;

import com.onionshop.entities.Project;
import com.onionshop.managers.UndoRedoManager;
import com.onionshop.controllers.NewProjectController;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.File;
import java.io.IOException;

public class UndoRedoManagerTest {
    Project p;

    @BeforeEach
    public void setUp() throws Exception {
        p = new Project("test", 2, 2);
    }
}
