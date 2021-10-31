package com.onionshop.managers;

import com.onionshop.entities.Project;
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
    }

    @AfterEach
    void cleanUpEach() {
        (new File(savePath)).delete();
    }

    @Test
    void saveProject() throws IOException {
        assert(OnionFileLoader.saveProject(project));
    }

    @Test
    void loadProject() throws Exception {
        OnionFileLoader.saveProject(project);
        assertArrayEquals(project.serialize(), OnionFileLoader.loadProject(savePath).serialize());
    }

    @Test
    void doesFileAlreadyExist() throws IOException {
        OnionFileLoader.saveProject(project);
        assert(OnionFileLoader.doesFileAlreadyExist(savePath));
    }
}