package com.onionshop.managers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class MostRecentProjectManagerTest {
    static MostRecentProjectManager manager;
    static String defaultPath;
    static String operatingSystem;

    @BeforeEach
    public void setUp() {
        manager = new MostRecentProjectManager();
        defaultPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
        operatingSystem = System.getProperty("os.name");
    }

    @Test
    public void testLoadMostRecentProjects() {
        File mostRecentProjectFile;

        if (operatingSystem.substring(0, 3).equals("Mac")) {
            mostRecentProjectFile = new File(defaultPath + "/Documents/fresh-onions.txt");
        } else {
            mostRecentProjectFile = new File(defaultPath + "\\fresh-onions.txt");
        }
        manager.loadMostRecentProjects();
        assertEquals(mostRecentProjectFile, manager.getMostRecentProjectFile());
    }
}
