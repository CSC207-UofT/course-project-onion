package com.onionshop.entities;

public class Line extends Shape implements Tool {

    /**
     * The Line class as a child of the Shape abstract class draws a line from the starting point to the ending
     * point of the user clicks on canvas.
     */


    public Line(int brushSize) {
        super(1);
        this.calculateEffectedPixels();
    }

    /**
     * Calculates the slope of the line connecting the start coordinate and the end coordinate by the distance between
     * them.
     *
     * @param distances     the distance (distance x, distance y) between the start and end coordinates
     * @return              the slope of the line as a double
     */
    public double calculateSlope(int[] distances) {
        // the slope is rise/run
        return (double) distances[1] / distances[0];
    }

    /**
     * Calculate result of the equation of the line by both the y or x value per inputted coordinate value depends on
     * what is needed.
     *
     * @param coordinateValue       Can either be x or y coordinate, depending on what is needed
     * @param distances             The distance between the start and end coordinates
     * @return                      result of the equation of the line (x result, y result) connecting start and end
     *                              coordinates
     */
    public double[] lineCalculationFormula(int coordinateValue, int[] distances) {
        // equation of the line evaluated
        double[] equation = new double[2];
        double m = calculateSlope(distances);

        // function of the line without transformations in terms of y
        equation[0] = (double) coordinateValue * m;
        // function of the line without transformations in terms of x
        equation[1] = (double) coordinateValue / m;

        return equation;
    }

    /**
     * The method calculateEffectedPixels is a part of the shape drawing flow and functions according to the drawStage
     * of the user input (See Shape class explanation)
     *
     * The purpose of calculateEffectedPixels is to calculate and update the variable <pixelsEffectedByShape> by the
     * coordinates of the shape which must be drawn at which drawStage.
     *
     * When drawStage = 0,
     *      As a result of such stage being the default stage where the user have not yet started building the shape,
     *      we reuse the same calculation as per brush tool's calculateEffectedPixels to possibly draw a square when
     *      draw() is called.
     *
     * When drawStage = 2,
     *      calculateEffectedPixels calculates and updates <pixelsEffectedByShape> by the coordinates of the line. The
     *      calculation is as follows:
     *      1. Calculate the line referring to the x-axis, by incrementing the x coordinate by 1 each time and evaluate
     *      the y coordinate using lineCalculationFormula per each increment.
     *      2. Calculate the line referring to the y-axis, by incrementing the y coordinate by 1 each time and evaluate
     *      the x coordinate using lineCalculationFormula per each increment.
     *
     * This provides us with the coverage of extreme cases such as when functions: x = constant or when y = constant
     * occur.
     */
    public void calculateEffectedPixels() {
        // Provide the coordinates of a dot to be drawn by draw() if the drawStage is 0
        if (drawStage == 0) {
            int boxSize = 5 * 5;
            pixelsEffectedByShape = new int[boxSize][2];

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
        } else if (drawStage == 3) {

            // import the start and end coordinate distances
            int[] distances = calculateStartEndDistance();

            pixelsEffectedByShape = new int[Math.abs(distances[0]) + Math.abs(distances[1]) + 2][2];

            // a counter for exiting the while loop when we arrive at the end coordinate
            int xCounter = 0;

            // while loop stop condition: when counter arrives at the x distance between start and end coordinates
            while (Math.abs(xCounter) <= Math.abs(distances[0])) {
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
            while (Math.abs(yCounter) <= Math.abs(distances[1])) {

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
