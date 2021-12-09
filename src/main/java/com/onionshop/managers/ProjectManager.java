package com.onionshop.managers;

import com.onionshop.entities.DrawingState;
import com.onionshop.entities.Pixel;
import com.onionshop.entities.Layer;
import com.onionshop.entities.Project;
import com.onionshop.events.NewProjectEvent;

import java.io.File;

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
        String path = newProjectEvent.getDirectory() + '/' + newProjectEvent.getProjectName() + ".onion";
        if (!OnionFileLoader.doesFileAlreadyExist(path)) {
            currentProject = new Project(path, newProjectEvent.getWidth(), newProjectEvent.getHeight());
            OnionFileLoader.saveProject(currentProject);
            this.updateDrawingCanvas(currentProject.getPixelArray(), false, false);
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
        this.updateDrawingCanvas(currentProject.getPixelArray(), false, false);
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
     * @param newCanvas a new Canvas which represent in 2d pixel array
     */
    public void updateDrawingCanvas(Pixel[][] newCanvas, boolean layerCreated, boolean layerDeleted) {
        if (newCanvas.length == currentProject.getWidth() && newCanvas[0].length == currentProject.getHeight()) {
            drawingState = new DrawingState(newCanvas, currentProject.layers.indexOf(currentProject.getCurrLayer()),
                    layerCreated, layerDeleted);
            undoRedoState.update(drawingState);
        }
        else {
            throw new IndexOutOfBoundsException("Updated drawingCanvas does not match initialized drawingCanvas size");
        }
    }

    /**
     * Add a new layer
     * @param layer: the new layer
     */
    public void addLayer(Layer layer, LayerManager layerManager){
        layerManager.addLayer(layer);
    }

    /**
     * Removes an existing layer
     * @param layer: the layer to be removed
     */
    public void removeLayer(Layer layer, LayerManager layerManager){
        layerManager.removeLayer(layer);
    }

    /**
     * Creates a default white layer
     */
    public void newLayer(LayerManager layerManager){
        layerManager.newLayer();
    }

    /**
     * Creates a layer with a specified background color
     * @param layerRGB: the background color
     */
    public void newLayer(int[] layerRGB, LayerManager layerManager){
        layerManager.newLayer(layerRGB);
    }

    /**
     * Selects a layer by selecting the layer object
     * @param layer: the layer to be selected
     */
    public void selectLayer(Layer layer, LayerManager layerManager){
        layerManager.selectLayer(layer);
    }

    /**
     * Selects a layer by selecting its index in layers list
     * @param index: the index of the layer to be selected
     */
    public void selectLayer(int index, LayerManager layerManager){
        layerManager.selectLayer(index);
    }

    /**
     * Returns the current project
     * @return the Current Project
     */
    public Project getCurrentProject() {
        return currentProject;
    }

    /**
     * Returns the current layer
     * @return the current layer
     */
    public Layer getCurrentLayer(LayerManager layerManager){
        return layerManager.getSelectedLayer();
    }

    public int getCurrentLayerIndex() { return currentProject.layers.indexOf(currentProject.getCurrLayer()); }

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
    public int undoDrawingState() {
        DrawingState topState = undoRedoState.getTopUndoStack();
        if (topState.getLayerCreated() || topState.getLayerDeleted()) {
            undoRedoState.undo();
            currentProject.setDrawingCanvasByLayer(topState.getState(), topState.getLayerIndex(),
                    topState.getLayerCreated(), topState.getLayerDeleted());
            if (topState.getLayerDeleted()) {
                return 1;
            }
            else {
                return 2;
            }
        }
        else if (undoRedoState.getTopUndoStack().getLayerIndex() == topState.getLayerIndex()) {
            DrawingState lastState = undoRedoState.undo();
            currentProject.setDrawingCanvasByLayer(lastState.getState(), lastState.getLayerIndex(),
                    topState.getLayerCreated(), topState.getLayerDeleted());
            return 0;
        }
        else {
            undoRedoState.undo();
            return 0;
        }

    }

    public int getUndoDrawingStateIndex() {
        return undoRedoState.getTopUndoStack().getLayerIndex();
    }
    public int getRedoDrawingStateIndex() {
        return undoRedoState.getTopRedoStack().getLayerIndex();
    }

    /**
     * Redo the drawing and restore the canvas.
     */
    public int restoreDrawingState(){
        DrawingState nextState = undoRedoState.redo();
        currentProject.setDrawingCanvasByLayer(nextState.getState(), nextState.getLayerIndex(),
                !nextState.getLayerCreated(), !nextState.getLayerDeleted());
        if (!nextState.getLayerDeleted() && !nextState.getLayerCreated()) {
            return 0;
        }
        else if (nextState.getLayerDeleted()) {
            return 1;
        }
        else {
            return 2;
        }


    }

}