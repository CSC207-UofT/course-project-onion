package com.onionshop.events;

import com.onionshop.managers.DrawingManager;
import com.onionshop.managers.LayerManager;

public class LayerEvents {
    DrawingManager currentDrawingManager;
    LayerManager currentLayerManager;
    //This is used to convert between our colours (0-255) and javaFx's colors (0.0-1.0)
    private final float conversionValue = 255;

    /**
     * Creates an instance of LayerEvents with the given DrawingManager
     */
    public LayerEvents(DrawingManager projectDrawingManager){
        this.currentDrawingManager = projectDrawingManager;
        this.currentLayerManager = projectDrawingManager.getLayerManager();
    }

    /**
     * Adds a layer to the LayerManger
     */
    public void processAddLayer() {
        currentLayerManager.newLayer();
    }


    /**
     * Removes a layer from the LayerManger
     */
    public void processRemoveLayer(int layerIndexToRemove) {
        currentLayerManager.removeLayer(layerIndexToRemove);
    }


    /**
     * Makes a layer selected in the LayerManager
     */
    public void processSelectLayer(int layerSelectedIndex) {
        currentLayerManager.selectLayer(layerSelectedIndex);
    }
}
