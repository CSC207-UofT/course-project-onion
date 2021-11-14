package com.onionshop.managers;

import com.onionshop.entities.Project;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class OnionFileLoaderTest {
    static Project project;
    static String savePath;

    @TempDir
    static Path tempDir1;

    @BeforeAll
    static void setUp() throws IOException {
        Path tempFilePath = Files.createFile(tempDir1.resolve("testFile1"));
        savePath = tempFilePath.toString();
        project = new Project(savePath, 2, 2);
    }

    @Test
    void saveProject() throws IOException {
        assert (OnionFileLoader.saveProject(project));
    }

    @Test
    void loadProject() throws Exception {
        OnionFileLoader.saveProject(project);
        assertArrayEquals(project.serialize(), OnionFileLoader.loadProject(savePath).serialize());
    }

    @Test
    void doesFileAlreadyExist() throws IOException {
        OnionFileLoader.saveProject(project);
        assert (OnionFileLoader.doesFileAlreadyExist(savePath));
    }
}