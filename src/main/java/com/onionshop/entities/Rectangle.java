package com.onionshop.entities;

public class Rectangle extends Shape implements Tool {

    /**
     * Creates a new rectangle Tool
     * @param brushSize The size of the line for the tool (not very relevant for the rectangle tool)
     */
    public Rectangle(int brushSize) {
        super(1);
        this.calculateEffectedPixels();
    }

    /**
     * Calculates the pixels that will be drawn on. The first drawstage places a dot where the user first clicks. The
     * second draw stage takes the start and end positions of the rectangle and calculates all of the pixels that are in
     * the rectangle.
     */
    public void calculateEffectedPixels() {
        if (drawStage == 0) {
            pixelsEffectedByShape = new int[121][2];

            int counter = 0;
            for (int x = -2; x < 3; x++) {
                for (int y = -2; y < 3; y++) {
                    this.pixelsEffectedByShape[counter][0] = x;
                    this.pixelsEffectedByShape[counter][1] = y;
                    counter++;
                }
            }

            // is incremented to enter stage 1 where we await for user's first input
            drawStage ++;

            // Initiated when the user makes the second click on the canvas
        } else if (drawStage == 2) {

            //Get the width and height of the rectangle
            int width = Math.abs(startingCoordinate[0] - endingCoordinate[0]);
            int height = Math.abs(startingCoordinate[1] - endingCoordinate[1]);

            //Get the top left corner of the rectangle to start drawing pixels from
            int[] newStartingCoordinate = new int[]{Math.min(startingCoordinate[0], endingCoordinate[0]),
                    Math.min(endingCoordinate[1], startingCoordinate[1])};

            //Iterate through the size of the whole rectangle and save each pixel.
            pixelsEffectedByShape = new int[width * height][2];
            int counter = 0;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    pixelsEffectedByShape[counter][0] = newStartingCoordinate[0] + x;
                    pixelsEffectedByShape[counter][1] = newStartingCoordinate[1] + y;
                    counter++;
                }
            }


        }
    }

}
