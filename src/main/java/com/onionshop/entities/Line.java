package com.onionshop.entities;

public class Line extends Shape implements Tool {

    public Line(String shapeType, int brushSize) {
        super("line", 1);
    }

    private double lineCalculationFormula(int x) {
        double m = calculateSlope();
        return x * m;
    }

    public void calculateEffectedPixels() {
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
