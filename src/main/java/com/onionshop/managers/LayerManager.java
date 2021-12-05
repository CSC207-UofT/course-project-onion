package com.onionshop.managers;

import com.onionshop.entities.Layer;
import com.onionshop.entities.Project;

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
        this.layers = currentProject.layers;
        this.currentProject = currentProject;

        newLayer(new int[]{255, 255, 255, 255});

    }


    public int getSelectedLayerIndex() {
        return selectedLayerIndex;
    }

    /**
     * Returns a Layer instance of the current selected layer.
     *
     */
    public Layer getSelectedLayer() {
        return this.layers.get(this.selectedLayerIndex);
    }

    /**
     * When the user creates a new layer, add a new blank canvas to the layers list
     */
    public void newLayer() {
        layers.add(new Layer(currentProject.getWidth(), currentProject.getHeight(), new int[]{0, 0, 0, 0}));
    }

    /**
     * When the user creates a new layer, add a new coloured canvas to the layers list
     *
     * @param layerRGB The colour to which the entire layer will be set
     */
    public void newLayer(int[] layerRGB) {
        layers.add(new Layer(currentProject.getWidth(), currentProject.getHeight(), layerRGB));
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    /**
     * When the user deletes a layer, remove the corresponding canvas from the layers list
     *
     * @param layer: the new layer to be removed
     *               Precondition: layer in layers
     */
    public void removeLayer(Layer layer) {
        this.layers.remove(layer);
    }

    /**
     * Set selected layer so that when user draws on the board, he is drawing on the correct layer
     *
     * @param index the index of the new selected Layer
     */
    public void selectLayer(int index) {
        if (0 <= index && index < layers.size()) {
            this.selectedLayerIndex = index;
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
    }


}