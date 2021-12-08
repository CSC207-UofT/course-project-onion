package com.onionshop.entities;

import com.onionshop.entities.Pixel;

public class DrawingState {
    /**
     *create a new memento that contain the current Drawing State information.
     */

    private final Pixel[][] state;
    private int layerIndex;
    private boolean newLayerCreated;
    private boolean layerDeleted;

    /**
     * take the pixel array and create the drawing state.
     * @param state the pixel array of current drawing state
     */
    public DrawingState(Pixel[][] state, int layerIndex, boolean newLayerCreated, boolean layerDeleted){
        this.state = state;
        this.layerIndex = layerIndex;
        this.newLayerCreated = newLayerCreated;
        this.layerDeleted = layerDeleted;
    }


    /**
     * get the pixel array of  drawing state.
     * @return return the pixel array of drawing state
     */
    public Pixel[][] getState(){
        return this.state;
    }

    public int getLayerIndex() { return this.layerIndex; }

    public boolean getLayerCreated() { return this.newLayerCreated; }

    public boolean getLayerDeleted() { return this.layerDeleted; }
}
