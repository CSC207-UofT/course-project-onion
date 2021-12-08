package com.onionshop.managers;

import com.onionshop.entities.Layer;
import com.onionshop.entities.Project;

import java.util.ArrayList;
import java.util.List;

public class LayerManager {
    /**
     * Save and manage layers created in an onion file
     */

    // the list of layers (layer ~ Project objects)
    public List<Layer> layers;

    // the current layer selected by the user
    private int selectedLayerIndex;

    //The current working project instance
    private Project currentProject;

    public LayerManager(Project currentProject) {
        this.layers = currentProject.getLayers();
        this.currentProject = currentProject;
    }


    public int getSelectedLayerIndex() {
        return selectedLayerIndex;
    }

    /**
     * Returns a Layer instance of the current selected layer.
     *
     */
    public Layer getSelectedLayer() {
        return currentProject.getLayers().get(this.selectedLayerIndex);
    }

    /**
     * When the user creates a new layer, add a new blank canvas to the layers list
     */
    public void newLayer() {
        currentProject.getLayers().add(new Layer(currentProject.getWidth(), currentProject.getHeight(), new int[]{0, 0, 0, 0}));
    }

    /**
     * When the user creates a new layer, add a new coloured canvas to the layers list
     *
     * @param layerRGB The colour to which the entire layer will be set
     */
    public void newLayer(int[] layerRGB) {
        currentProject.getLayers().add(new Layer(currentProject.getWidth(), currentProject.getHeight(), layerRGB));
    }

    public void addLayer(Layer layer) {
        currentProject.getLayers().add(layer);
    }

    /**
     * When the user deletes a layer, remove the corresponding canvas from the layers list
     *
     * @param layer: the new layer to be removed
     *               Precondition: layer in layers
     */
    public void removeLayer(Layer layer) {
        currentProject.getLayers().remove(layer);
    }

    /**
     * When the user deletes a layer, remove the corresponding canvas from the layers list
     *
     * @param layerIndex: the layer index to be removed
     */
    public void removeLayer(int layerIndex) {
        currentProject.getLayers().remove(layerIndex);
    }

    /**
     * Set selected layer so that when user draws on the board, he is drawing on the correct layer
     *
     * @param index the index of the new selected Layer
     */
    public void selectLayer(int index) {
        if (0 <= index && index < currentProject.getLayers().size()) {
            this.selectedLayerIndex = index;
            currentProject.setCurrLayer(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Set selected layer so that when user draws on the board, he is drawing on the correct layer
     *
     * @param layer the layer to be selected
     */
    public void selectLayer(Layer layer) {
        selectedLayerIndex = layers.indexOf(layer);
        currentProject.setCurrLayer(selectedLayerIndex);
    }


}