package com.onionshop.events;

import com.onionshop.managers.DrawingManager;
import com.onionshop.managers.LayerManager;
import com.onionshop.managers.ProjectManager;

public class LayerEvents {
    DrawingManager currentDrawingManager;
    LayerManager currentLayerManager;
    private final ProjectManager projectManager = ProjectManager.getInstance();
    //This is used to convert between our colours (0-255) and javaFx's colors (0.0-1.0)
    private final float conversionValue = 255;

    public LayerEvents(DrawingManager projectDrawingManager){
        this.currentDrawingManager = projectDrawingManager;
        this.currentLayerManager = projectDrawingManager.getLayerManager();
    }

    public void processAddLayer() {
        currentLayerManager.newLayer();
        int lastSelectedLayer = currentLayerManager.getSelectedLayerIndex();
        currentLayerManager.selectLayer(currentLayerManager.layers.size() - 1);
        projectManager.updateDrawingCanvas(projectManager.getCurrentProject().getPixelArray(),
                true, false);
        currentLayerManager.selectLayer(lastSelectedLayer);
    }

    public void processRemoveLayer(int layerIndexToRemove) {
        int lastSelectedLayer = currentLayerManager.getSelectedLayerIndex();
        currentLayerManager.selectLayer(layerIndexToRemove);
        projectManager.updateDrawingCanvas(projectManager.getCurrentProject().getPixelArray(),
                false, true);
        currentLayerManager.selectLayer(lastSelectedLayer);
        currentLayerManager.removeLayer(layerIndexToRemove);

    }

    public void processSelectLayer(int layerSelectedIndex) {
        currentLayerManager.selectLayer(layerSelectedIndex);
        projectManager.updateDrawingCanvas(projectManager.getCurrentProject().getPixelArray(),
                false, false);
    }

    public void processSelectLayerByIndex(int layerSelectedIndex) {
        currentLayerManager.selectLayer(layerSelectedIndex);
    }
}
