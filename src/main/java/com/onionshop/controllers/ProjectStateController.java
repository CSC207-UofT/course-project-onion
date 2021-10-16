package com.onionshop.controllers;

import com.onionshop.Brush;
import com.onionshop.Colour;
import com.onionshop.Pixel;
import com.onionshop.Project;

/**
 * records the action -> trigger methods -> send data to backend
 * Actions include: selecting a tool on the toolbar(ToolbarEvents - to be implemented), updating brush/pen sizes(Pen),
 * drawing on canvas and selecting a colour(to be implemented).
 */
public class ProjectStateController {

    /**
     * Takes in a ToolbarEvent, update the tool accordingly
     * To be implemented after phase0
     */
    public void ToolUpdate(){

    }

    /**
     * Takes in an event that changes the size of brush, update the brush size accordingly
     * @param brush: the brush object
     */
    public void BrushSizeUpdate(Brush brush, int newSize){
        brush.setBrushSize(newSize);

    }

    /**
     * Update the canvas using the data passed down
     * @param canvas : a 2d-array of Pixel objects representing each pixel of the drawing canvas
     * @param pixels: a 2d-array of locations of pixels that need to be updated (in phase0 they are all updated to black)
     */

    public void CanvasUpdate(Pixel[][] canvas, int[][] pixels){
        //for(int i=0; i< pixels.length; i++){



        //}


    }

    /**
     * Takes in an event that picks a colour and updates the colour
     * @param colour: the colour object
     * @param newColour : integer array of the RGB value of the new colour
     */
    public void ColourUpdate(Colour colour, int[] newColour){
        colour.setRGB(newColour);
    }




}
