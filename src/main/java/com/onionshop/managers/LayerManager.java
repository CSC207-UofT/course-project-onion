package com.onionshop.managers;

import com.onionshop.entities.Layer;
import com.onionshop.entities.Project;

import java.util.ArrayList;
import java.util.List;

public class LayerManager {
    /**
     * Save and manage layers created in an onion file
     */

    // the current layer selected by the user
    private int selectedLayerIndex = 0;
    ProjectManager projectManager = ProjectManager.getInstance();
    static LayerManager instance = new LayerManager();


    /**
     * Returns a LayerManager instance
     *
     */
    public static LayerManager getInstance() {
        return instance;
    }


    public int getSelectedLayerIndex() {
        return selectedLayerIndex;
    }

    /**
     * Returns a Layer instance of the current selected layer.
     *
     */
    public Layer getSelectedLayer() {
        return projectManager.getCurrentProject().getLayers().get(this.selectedLayerIndex);
    }

    /**
     * When the user creates a new layer, add a new blank canvas to the layers list
     */
    public void newLayer() {
        projectManager.getCurrentProject().getLayers().add(new Layer(projectManager.getCurrentProject().getWidth(), projectManager.getCurrentProject().getHeight(), new int[]{0, 0, 0, 0}));
    }

    /**
     * When the user creates a new layer, add a new coloured canvas to the layers list
     *
     * @param layerRGB The colour to which the entire layer will be set
     */
    public void newLayer(int[] layerRGB) {
        projectManager.getCurrentProject().getLayers().add(new Layer(projectManager.getCurrentProject().getWidth(), projectManager.getCurrentProject().getHeight(), layerRGB));
    }

    public void addLayer(Layer layer) {
        projectManager.getCurrentProject().getLayers().add(layer);
    }

    /**
     * When the user deletes a layer, remove the corresponding canvas from the layers list
     *
     * @param layer: the new layer to be removed
     *               Precondition: layer in layers
     */
    public void removeLayer(Layer layer) {
        projectManager.getCurrentProject().getLayers().remove(layer);
    }

    /**
     * When the user deletes a layer, remove the corresponding canvas from the layers list
     *
     * @param layerIndex: the layer index to be removed
     */
    public void removeLayer(int layerIndex) {
        projectManager.getCurrentProject().getLayers().remove(layerIndex);
    }

    /**
     * Set selected layer so that when user draws on the board, he is drawing on the correct layer
     *
     * @param index the index of the new selected Layer
     */
    public void selectLayer(int index) {
        if (0 <= index && index < projectManager.getCurrentProject().getLayers().size()) {
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
        selectedLayerIndex = projectManager.getCurrentProject().getLayers().indexOf(layer);
    }


}