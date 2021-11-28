package com.onionshop.managers;

import com.onionshop.entities.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LayerManager{
    /**
     * Save and manage layers created in an onion file
     */


    // the list of layers (layer ~ Project objects)
    private List<Project> layers;

    // the current layer selected by the user
    private Project selectedLayer;

    public LayerManager(){
        this.layers = new ArrayList<>();

    }

    /**
     * When the user creates a new layer, add a new blank canvas to the layers list
     * @param layer : the new layer to be added
     */
    public void addLayer(Project layer){
        this.layers.add(layer);
    }

    /**
     * When the user deletes a layer, remove the corresponding canvas from the layers list
     * @param layer: the new layer to be removed
     * Precondition: layer in layers
     */
    public void removeLayer(Project layer){
        this.layers.remove(layer);

    }

    /**
     * Set selected layer so that when user draws on the board, he is drawing on the correct layer
     * @param layer
     * Precondition: layer in layers
     */
    public void selectLayer(Project layer){
        this.selectedLayer = layer;
    }



}
