package com.onionshop.entities;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTest {
    Project p;

    @TempDir
    Path tempDir1;
    String savePath;

    @BeforeEach
    public void setUp() throws Exception {
        Path tempFilePath = Files.createFile(tempDir1.resolve("project_name.onion"));
        savePath = tempFilePath.toString();
        p = new Project(savePath, 2, 2);
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
                "[layer:0]",
                "255,255,255,255",
                "255,255,255,255",
                "255,255,255,255",
                "255,255,255,255",
                "[end]"};
        assertArrayEquals(lines, expected);
    }

    @Test
    void extractProjectName() {
        assertEquals(p.extractProjectName(), "project_name");
    }
}
