package com.onionshop.managers;

import com.onionshop.entities.DrawingState;
import com.onionshop.entities.Layer;
import com.onionshop.entities.Pixel;
import com.onionshop.entities.Project;
import com.onionshop.events.NewProjectEvent;

public class ProjectManager {
    /**
        Manages the currently opened project.
     */

    private final static ProjectManager instance = new ProjectManager();
    private Project currentProject;
    private LayerManager layerManager;
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
        } else {
            throw new Exception("Error: File with that name already exists!");
        }

        layerManager = new LayerManager(currentProject);
    }

    /**
     * Adds a given layer to the project by calling method in LayerManager
     * @param layer: the layer
     */
    public void addLayer(Layer layer) {
        layerManager.addLayer(layer);
    }

    /**
     * Creates a new layer in the project without a specified background color
     */
    public void newLayer(){
        layerManager.newLayer();
    }

    /**
     * Creates a new layer in the project with a background color
     * @param layerRGB: the background color
     */
    public void newLayer(int[] layerRGB) {
        layerManager.newLayer(layerRGB);
    }

    /**
     * Removes an existing layer by calling method in LayerManager
     * @param layer: the layer
     */
    public void removeLayer(Layer layer){
        layerManager.removeLayer(layer);
    }

    /**
     * Selects a layer by selecting its index in the layers list
     * @param index: the index in the layers list
     */
    public void selectLayer(int index){
        layerManager.selectLayer(index);
    }

    /**
     * Selects a layer by directly selecting the layer object
     * @param layer: the layer
     */
    public void selectLayer(Layer layer){
        layerManager.selectLayer(layer);
    }


    /**
     * Loads the selectedOnionFile as the currentProject
     * @param selectedOnionFilePath a .onion file
     */
    public void loadProject(String selectedOnionFilePath) throws Exception {
        currentProject = OnionFileLoader.loadProject(selectedOnionFilePath);
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
     * @param updatedLayer a new Canvas which represent in 2d pixel array
     */
    public void updateDrawingCanvas(Pixel[][] updatedLayer) {
        if (updatedLayer.length == currentProject.getWidth() && updatedLayer[0].length == currentProject.getHeight()) {
            currentProject.layers.get(layerManager.getSelectedLayerIndex()).setLayerCanvas(updatedLayer);
            drawingState = new DrawingState(updatedLayer);
            undoRedoState.update(drawingState);

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

    /**
     * Returns the selected layer
     * @return the selected layer
     */
    public Layer getCurrentLayer(){
        return layerManager.getSelectedLayer();
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
        currentProject.layers.get(layerManager.getSelectedLayerIndex()).setLayerCanvas(undoRedoState.undo().getState());

    }

    /**
     * Redo the drawing and restore the canvas.
     */
    public void restoreDrawingState(){
        currentProject.layers.get(layerManager.getSelectedLayerIndex()).layerCanvas = undoRedoState.redo().getState();

    }

}
