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
     * Calculates the pixels that will be drawn on.
     */
    public void calculateEffectedPixels() {
        if (drawStage == 3) {

            //Get the width of the clicked positions
            int width = Math.abs(startingCoordinate[0] - endingCoordinate[0]);

            //Get center x coordinate of the circle
            double centerX = (double) (width) / 2;

            //Get the top left corner of the circle to start drawing pixels from
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

                // adding of four quarters of the circle
                this.pixelsEffectedByShape[counter][0] = newStartingCoordinate[0] + counter;
                this.pixelsEffectedByShape[counter][1] = newStartingCoordinate[1] + (int) (centerX - calculation);

                this.pixelsEffectedByShape[counter + width][0] = newStartingCoordinate[0] + counter;
                this.pixelsEffectedByShape[counter + width][1] = newStartingCoordinate[1] +
                        (int) (centerX + calculation);

                this.pixelsEffectedByShape[counter + width * 2][0] = newStartingCoordinate[0]
                        + (int) (centerX + calculation);
                this.pixelsEffectedByShape[counter + width * 2][1] = newStartingCoordinate[1] + counter;

                this.pixelsEffectedByShape[counter + width * 3][0] = newStartingCoordinate[0]
                        + (int) (centerX - calculation);
                this.pixelsEffectedByShape[counter + width * 3][1] = newStartingCoordinate[1] + counter;

                counter++;
                plotCounter++;
            }
        }
        else {
            pixelsEffectedByShape = new int[0][0];
        }
    }
}
