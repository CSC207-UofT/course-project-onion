package com.onionshop.entities;

public class Line extends Shape implements Tool {

    public Line(int brushSize) {
        super(1);
        this.calculateEffectedPixels();
    }

    private double lineCalculationFormula(int x) {
        double m = calculateSlope();
        return x * m;
    }

    public void calculateEffectedPixels() {
        // want to draw a red dot if the drawStage is 0
        if (drawStage == 0) {
            pixelsEffectedByShape = new int[121][2];

            int counter = 0;
            for (int x = -5; x < 6; x++) {
                for (int y = -5; y < 6; y++) {
                    this.pixelsEffectedByShape[counter][0] = x;
                    this.pixelsEffectedByShape[counter][1] = y;
                    counter++;
                }
            }

            drawStage = 1;
        } else if (drawStage == 2) {
            pixelsEffectedByShape = new int[121][2];

            // import the start and end coordinate distances
            int[] distances = calculateStartEndDistance();

            // a counter for exiting the while loop when we arrive at the end coordinate
            int xCounter = 0;
            // while loop stop condition: when counter arrives at the x distance between start and end coordinates
            while (xCounter < distances[1]) {
                this.pixelsEffectedByShape[xCounter][0] = startingCoordinate[0] + xCounter;
                this.pixelsEffectedByShape[xCounter][1] = (int) (startingCoordinate[1] + lineCalculationFormula(xCounter));
                xCounter++;
            }

            assert Math.abs(this.pixelsEffectedByShape[xCounter - 1][0] - endingCoordinate[0]) < 5;
            assert Math.abs(this.pixelsEffectedByShape[xCounter - 1][1] - endingCoordinate[1]) < 5;
        }
    }


}
