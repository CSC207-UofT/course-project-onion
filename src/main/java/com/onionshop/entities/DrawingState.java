package com.onionshop.entities;

import com.onionshop.entities.Pixel;

public class DrawingState {
    /**
     *create a new memento that contain the current Drawing State information.
     */

    private Pixel[][] State;

    /**
     * take the pixel array and create the drawing state.
     * @param State the pixel array of current drawing state
     */
    public DrawingState(Pixel[][] State){
        this.State = State;
    }

    /**
     * get the pixel array of  drawing state.
     * @return return the pixel array of drawing state
     */
    public Pixel[][] getState(){
        return State;
    }
}
