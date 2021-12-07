package com.onionshop.events;

import com.onionshop.managers.DrawingManager;
import com.onionshop.managers.LayerManager;

public class LayerEvents {
    DrawingManager currentDrawingManager;
    LayerManager currentLayerManager;
    //This is used to convert between our colours (0-255) and javaFx's colors (0.0-1.0)
    private final float conversionValue = 255;

    public LayerEvents(DrawingManager projectDrawingManager){
        this.currentDrawingManager = projectDrawingManager;
        this.currentLayerManager = projectDrawingManager.getLayerManager();
    }

    public void processAddLayer() {
        currentLayerManager.newLayer();
    }

    public void processRemoveLayer(int layerIndexToRemove) {
        currentLayerManager.removeLayer(layerIndexToRemove);
    }

    public void processSelectLayer(int layerSelectedIndex) {
        currentLayerManager.selectLayer(layerSelectedIndex);
    }
}
