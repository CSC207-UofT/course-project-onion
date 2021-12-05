package com.onionshop.entities;

public interface Tool {
    /**
     *  Decides how the tool is supposed to be outputted onto the canvas.
     **/
    int[][] draw(Layer currentCanvas, Colour currentColour, int x, int y);

    /**
     *  Calculates the pixels the brush or tool will update based on its current size and shape
     */
    void calculateEffectedPixels();
}
