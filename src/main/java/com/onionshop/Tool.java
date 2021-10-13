package com.onionshop;

public interface Tool {
    /**
     *  Make the tool selectable by loading up the tool's option panel which may include brush size, brush shape.
     **/
    void makeSelectable();

    /**
     *  Decides how the tool is supposed to be outputted onto the canvas.
     **/
    void draw();
}
