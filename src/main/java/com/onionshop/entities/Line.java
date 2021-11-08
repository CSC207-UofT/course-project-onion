package com.onionshop.entities;

public class Line extends Shape implements Tool {

    public Line(int brushSize) {
        super(1);
        this.calculateEffectedPixels();
    }

    private double lineCalculationFormula(int x, int[] distances) {
        double m = calculateSlope(distances);

        System.out.println("what is the slope mate is it working " + m);

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

            drawStage ++;

        } else if (drawStage == 2) {

            System.out.println("ran?");

            System.out.println("did the coordinates pass start " + this.startingCoordinate[0]);
            System.out.println("did the coordinates pass start " + this.startingCoordinate[1]);

            System.out.println("did the coordinates pass end " + this.endingCoordinate[0]);
            System.out.println("did the coordinates pass end " + this.endingCoordinate[1]);

            pixelsEffectedByShape = new int[121][2];

            // import the start and end coordinate distances
            int[] distances = calculateStartEndDistance();

            System.out.println("What's the distance did it work " + distances[0] + " and " + distances[1]);

            // a counter for exiting the while loop when we arrive at the end coordinate
            int xCounter = 0;

            // while loop stop condition: when counter arrives at the x distance between start and end coordinates
            while (xCounter < Math.abs(distances[0])) {

                System.out.println("Lets see if this works " + lineCalculationFormula(xCounter, distances));
                System.out.println(xCounter);

                this.pixelsEffectedByShape[xCounter][0] = startingCoordinate[0] + xCounter;
                this.pixelsEffectedByShape[xCounter][1] =
                        (int) (startingCoordinate[1] + lineCalculationFormula(xCounter, distances));
                xCounter++;
            }

            for (int beef = 0; beef < pixelsEffectedByShape.length; beef++) {
                System.out.println("loopin x " + pixelsEffectedByShape[beef][0]);
                System.out.println("loopin y " + pixelsEffectedByShape[beef][1]);
            }

            assert Math.abs(this.pixelsEffectedByShape[xCounter - 1][0] - endingCoordinate[0]) < 5;
            assert Math.abs(this.pixelsEffectedByShape[xCounter - 1][1] - endingCoordinate[1]) < 5;
        }
    }


}
