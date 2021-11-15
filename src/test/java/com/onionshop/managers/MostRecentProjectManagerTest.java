package com.onionshop.managers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class MostRecentProjectManagerTest {
    MostRecentProjectManager manager;
    String defaultPath;

    @BeforeEach
    public void setUp() {
        manager = new MostRecentProjectManager();
        defaultPath = FileSystemView.getFileSystemView().getDefaultDirectory().getPath();
    }

    @Test
    public void testLoadMostRecentProjectsWindows() {
        File mostRecentProjectFile = new File(defaultPath + "\\fresh-onions.txt");
        manager.loadMostRecentProjects();
        assertEquals(mostRecentProjectFile, manager.getMostRecentProjectFile());
    }

    @Test
    public void testAddMostRecentProject() throws IOException {
//        manager.loadMostRecentProjects();
//        manager.addMostRecentProject("testOne.onion", "\"C:\\Users\\65rgr\\Documents\\testOne.onion\"");
//        String[][] mostRecentProjects = {{"testOne.onion", "\"C:\\Users\\65rgr\\Documents\\testOne.onion\""}};
//        System.out.println(mostRecentProjects);
//        System.out.println(manager.getMostRecentProjects());
//        assertEquals(mostRecentProjects, manager.getMostRecentProjects());
    }

    @Test
    public void testGetMostRecentProjects() {

    }
}
