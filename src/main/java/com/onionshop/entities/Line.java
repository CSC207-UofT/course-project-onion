package com.onionshop.entities;

import java.util.Collections;

public class Line extends Shape implements Tool {

    public Line(int brushSize) {
        super(1);
        this.calculateEffectedPixels();
    }

    private double[] lineCalculationFormula(int coordinateValue, int[] distances) {
        double[] equation = new double[2];
        double m = calculateSlope(distances);

        equation[0] = (double) coordinateValue * m;
        equation[1] = (double) coordinateValue / m;

        return equation;
    }

    public void calculateEffectedPixels() {
        // want to draw a red dot if the drawStage is 0
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

            drawStage ++;

        } else if (drawStage == 2) {

            // import the start and end coordinate distances
            int[] distances = calculateStartEndDistance();

            pixelsEffectedByShape = new int[2 * Math.max(Math.max(startingCoordinate[0], startingCoordinate[1]),
                    Math.max(endingCoordinate[0], endingCoordinate[1]))][2];

            // a counter for exiting the while loop when we arrive at the end coordinate
            int xCounter = 0;

            // while loop stop condition: when counter arrives at the x distance between start and end coordinates
            while (Math.abs(xCounter) < Math.abs(distances[0])) {
                this.pixelsEffectedByShape[Math.abs(xCounter)][0] = startingCoordinate[0] + xCounter;
                this.pixelsEffectedByShape[Math.abs(xCounter)][1] =
                        (int) (startingCoordinate[1] + lineCalculationFormula(xCounter, distances)[0]);

                if (distances[0] > 0) {
                    xCounter--;
                } else {
                    xCounter++;
                }

            }

            // a counter for exiting the while loop when we arrive at the end coordinate
            int yCounter = 0;

            // while loop stop condition: when counter arrives at the y distance between start and end coordinates
            while (Math.abs(yCounter) < Math.abs(distances[1])) {

                this.pixelsEffectedByShape[Math.abs(xCounter + yCounter)][0] =
                        (int) (lineCalculationFormula(yCounter, distances)[1] + startingCoordinate[0]);
                this.pixelsEffectedByShape[Math.abs(xCounter + yCounter)][1] = startingCoordinate[1] + yCounter;

                if (distances[1] > 0) {
                    yCounter--;
                } else {
                    yCounter++;
                }

            }

//            assert Math.abs(this.pixelsEffectedByShape[Math.abs(xCounter) - 1][0] - endingCoordinate[0]) < 5;
//            assert Math.abs(this.pixelsEffectedByShape[Math.abs(xCounter) - 1][1] - endingCoordinate[1]) < 5;
        }
    }


}
