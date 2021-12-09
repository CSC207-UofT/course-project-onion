package com.onionshop.managers;

import com.onionshop.entities.DrawingState;
import com.onionshop.entities.Layer;
import com.onionshop.entities.Project;
import com.onionshop.events.NewProjectEvent;

import java.util.ArrayList;
import java.util.List;

public class ProjectManager {
    /**
     Manages the currently opened project.
     */

    private final static ProjectManager instance = new ProjectManager();
    private Project currentProject;
    private final UndoRedoManager undoRedoState = new UndoRedoManager();
    private DrawingState drawingState;

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
        String path;
        String operatingSystem = System.getProperty("os.name");
        if (operatingSystem.substring(0, 3).equals("Mac")) {
            path = newProjectEvent.getDirectory() + '/' + newProjectEvent.getProjectName() + ".onion";
        } else {
            path = newProjectEvent.getDirectory() + '\\' + newProjectEvent.getProjectName() + ".onion";
        }
        if (!OnionFileLoader.doesFileAlreadyExist(path)) {
            currentProject = new Project(path, newProjectEvent.getWidth(), newProjectEvent.getHeight());
            OnionFileLoader.saveProject(currentProject);
            this.updateLayers(currentProject.getLayers());
        } else {
            throw new Exception("Error: File with that name already exists!");
        }
    }

    /**
     * Loads the selectedOnionFile as the currentProject
     * @param selectedOnionFilePath a .onion file
     */
    public void loadProject(String selectedOnionFilePath) throws Exception {
        currentProject = OnionFileLoader.loadProject(selectedOnionFilePath);
        this.updateLayers(currentProject.getLayers());
        System.out.println("Project:" + selectedOnionFilePath + " loaded");
    }

    public UndoRedoManager getUndoRedoState() {
        return undoRedoState;
    }

    /**
     * Saves  currentProject current changes to the .onion file at currentProject.path
     */
    public void saveProject() throws Exception {
        OnionFileLoader.saveProject(currentProject);
        System.out.println("Project:" + currentProject.getPath() + " saved");
    }

    /**
     * Update the project and UndoRedoManager with newest the pixel array.
     * @param layers the current layers
     */
    public void updateLayers(List<Layer> layers) {
        List<Layer> layersCopy = new ArrayList<>();

        for (Layer layer: layers) {
            Layer layerCopy = new Layer(currentProject.getWidth(), currentProject.getHeight(), new int[]{0, 0, 0, 0});
            layerCopy.setLayerCanvas(layer.layerCanvas);
            layersCopy.add(layerCopy);
        }

        drawingState = new DrawingState(layersCopy);
        undoRedoState.update(drawingState);
        currentProject.setLayers(layersCopy);
    }

    /**
     * Returns the current project
     * @return the Current Project
     */
    public Project getCurrentProject() {
        return currentProject;
    }

    /**
     * set the Drawing State.
     * @param drawingState a new drawing state
     */
    public void setDrawingState(DrawingState drawingState){
        this.drawingState = drawingState;
    }

    /**
     * Update the canvas if undo the Drawing State.
     */
    public void undoDrawingState() {
        DrawingState drawing = undoRedoState.undo();
        currentProject.setLayers(drawing.getState());
        this.drawingState = drawing;
    }

    /**
     * Redo the drawing and restore the canvas.
     */
    public void restoreDrawingState(){
        currentProject.setLayers(undoRedoState.redo().getState());
    }

}