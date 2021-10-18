package com.onionshop;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class OnionFileLoaderTest {
    static Project project;
    static String savePath;

    @BeforeAll
    static void setUp() throws IOException {
        savePath = new File(".").getCanonicalPath() + "/src/test/resources/project_save_test.onion";
        project = new Project(savePath, 2, 2);
        OnionFileLoader.saveProject(project);
    }

    @AfterAll
    static void tearDown() throws IOException {
        (new File(savePath)).delete();
    }

    @Test
    void saveProject() throws IOException {
        (new File(savePath)).delete();
        assert(OnionFileLoader.saveProject(project));
    }

    @Test
    void loadProject() throws IOException {
        assertArrayEquals(project.Serialize(), OnionFileLoader.loadProject(savePath).Serialize());
    }
}