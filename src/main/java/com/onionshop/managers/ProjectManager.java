package com.onionshop.managers;

import com.onionshop.entities.Pixel;
import com.onionshop.entities.Project;
import com.onionshop.events.NewProjectEvent;

import java.io.File;

public class ProjectManager {
    /**
        Manages the currently opened project.
     */

    private final static ProjectManager instance = new ProjectManager();
    private Project currentProject;
    private UndoRedoManager undoRedoState = new UndoRedoManager();

    /**
     * Returns the instance of ProjectManager
     * @return the instance of this singleton global ProjectManager
     */
    public static ProjectManager getInstance() {
        return instance;
    }

    /**
     * Creates a new project based on newProjectEvent and saves the project to new .onion file
     * @param newProjectEvent an event that describes the base properties of the new project.
     */
    public void newProject(NewProjectEvent newProjectEvent) throws Exception {
        String path = newProjectEvent.getDirectory() + '/' + newProjectEvent.getProjectName() + ".onion";
        if (!OnionFileLoader.doesFileAlreadyExist(path)) {
            currentProject = new Project(path, newProjectEvent.getWidth(), newProjectEvent.getHeight());
            OnionFileLoader.saveProject(currentProject);
        } else {
            throw new Exception("Error: File with that name already exists!");
        }
    }

    /**
     * Loads the selectedOnionFile as the currentProject
     * @param selectedOnionFile a .onion file
     */
    public void loadProject(File selectedOnionFile) throws Exception {
        currentProject = OnionFileLoader.loadProject(selectedOnionFile.getAbsolutePath());
        System.out.println("Project Loaded");
    }

    public void updateDrawingCanvas(Pixel[][] newCanvas) {
        if (newCanvas.length == currentProject.getWidth() && newCanvas[0].length == currentProject.getHeight()) {
            currentProject.drawingCanvas = newCanvas;
            undoRedoState.update(newCanvas);
        }
        else {
            throw new IndexOutOfBoundsException("Updated drawingCanvas does not match initialized drawingCanvas size");
        }
    }

    /**
     * Returns the current project
     * @return the Current Project
     */
    public Project getCurrentProject() {
        return currentProject;
    }
}
