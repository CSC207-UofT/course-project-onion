package com.onionshop.entities;

public class Circle extends Shape implements Tool {

    /**
     * Creates a new rectangle Tool
     */
    public Circle() {
        super(1);
        this.calculateEffectedPixels();
    }

    /**
     * Calculates the pixels that will be drawn on. The first drawstage places a dot where the user first clicks. The
     * second draw stage takes the start and end positions of the rectangle and calculates all of the pixels that are in
     * the boundary of the circle.
     */
    public void calculateEffectedPixels() {
        if (drawStage == 0) {
            pixelsEffectedByShape = new int[0][0];

            // is incremented to enter stage 1 where we await for user's first input
            drawStage ++;

            // Initiated when the user makes the second click on the canvas
        } else if (drawStage == 3) {

            //Get the width of the clicked positions
            int width = Math.abs(startingCoordinate[0] - endingCoordinate[0]);

            //Get center x coordinate of the circle
            double centerX = (double) (width) / 2;

            //Get the top left corner of the rectangle to start drawing pixels from
            int[] newStartingCoordinate = new int[]{Math.min(startingCoordinate[0], endingCoordinate[0]),
                    Math.min(endingCoordinate[1], startingCoordinate[1])};

            //Iterate through the size of the whole rectangle and save each pixel.
            pixelsEffectedByShape = new int[width * width * 2][2];
            // a counter for position on plot
            int plotCounter = - (int) (centerX);
            int counter = 0;

            // while loop stop condition: when counter arrives at the width distance
            while (counter <= width) {
                // the calculation of the circle
                double calculation = Math.sqrt(Math.pow((centerX), 2) - Math.pow((plotCounter), 2));

                this.pixelsEffectedByShape[counter][0] = newStartingCoordinate[0] + counter;
                this.pixelsEffectedByShape[counter][1] = newStartingCoordinate[1] + (int) (centerX - calculation);

                this.pixelsEffectedByShape[counter + width][0] = newStartingCoordinate[0] + counter;
                this.pixelsEffectedByShape[counter + width][1] = newStartingCoordinate[1] +
                        (int) (centerX + calculation);
                counter++;
                plotCounter++;
            }
        }
    }
}
