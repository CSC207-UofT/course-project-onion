package com.onionshop.entities;
import java.util.List;

public class DrawingState {
    /**
     *create a new memento that contain the current Drawing State information.
     */

    private final List<Layer> state;

    /**
     * take the pixel array and create the drawing state.
     * @param state the pixel array of current drawing state
     */
    public DrawingState(List<Layer> state){
        this.state = state;
    }

    /**
     * get the pixel array of  drawing state.
     * @return return the pixel array of drawing state
     */
    public List<Layer>  getState(){
        return this.state;
    }
}
