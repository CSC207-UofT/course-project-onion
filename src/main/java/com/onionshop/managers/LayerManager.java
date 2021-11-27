package com.onionshop.managers;

import com.onionshop.entities.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LayerManager{
    /**
     * Save and manage layers created in an onion file
     */

    // the list of layers (layer ~ 2D Pixel array)
    private List<Pixel[][]> layers;
    private List<Pixel[][]> hiddenLayers;


    public LayerManager(){
        this.layers = new ArrayList<>();
        this.hiddenLayers = new ArrayList<>();
    }

    /**
     * When the user creates a new layer, add a new blank canvas to the layers list
     * @param layer : the new layer to be added
     */
    public void addLayer(Pixel[][] layer){
        this.layers.add(layer);
    }

    /**
     * When the user deletes a layer, remove the corresponding canvas from the layers list
     * @param layer: the new layer to be removed
     * Precondition: layer in layers
     */
    public void removeLayer(Pixel[][] layer){
        this.layers.remove(layer);

    }

    /**
     * When the user unchecks a layer, make it invisible by storing its pixel values and then set it full white
     * @param layer: the layer to be hidden
     * Precondition: layer in layers
     */
    public void uncheckLayer(Pixel[][] layer){
        // temporary layer that stores the pixel values of the given layer
        Pixel[][] tempLayer = new Pixel[layer.length][layer[0].length];
        for (int x = 0; x < layer.length; x++){
            for (int y = 0; y < layer[0].length; y++){
                tempLayer[x][y] = layer[x][y];
                layer[x][y] = new Pixel(new int[]{255, 255, 255});
            }
        }
        this.hiddenLayers.add(tempLayer);

    }

    /**
     * When the user checks a layer, make it visible by removing it from the hiddenLayers list and adding it to the layers list
     * @param layer: the layer to be made visible
     * Precondition: layer in hiddenLayers
     */
    public void checkLayer(Pixel[][] layer){
        this.hiddenLayers.remove(layer);
        this.layers.add(layer);

    }
}
