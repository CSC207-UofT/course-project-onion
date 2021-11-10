package com.onionshop.entities;

public abstract class Shape implements Tool {

    /**
     * The Shape class is an abstract class where individual shapes extends from.
     * The actual design and implementation of the Shape class and its interaction with its child classes will be
     * explained below:
     *
     * === Expectation of the function of the Shape tool on canvas ===
     * 1. User clicks on canvas, creates a dot which acts as the starting point of any shape
     * 2. User clicks again on any point on the canvas again, the shape is drawn in between the start point and this
     *    point – the end point.
     *
     * === How it's implemented ===
     * startingCoordinate       the first coordinates which the user clicked on
     * endingCoordinate         the second coordinates which the user clicked on
     * drawStage                a variable that keeps track of which stage of the shape tool drawing process is
     *                          currently active
     *
     * === drawStage and each stages' purpose ===
     * When drawStage = 0
     *      This is the initial stage prior to the user drawing anything on the canvas
     *
     * When drawStage = 1
     *      This is the stage where the user have drawn the first dot, the canvas should display the starting point
     *      accordingly
     *
     * When drawStage = 2
     *      This is the stage where the user have clicked on the canvas a second time, the canvas should display the
     *      actual shape by calculation
     */

    protected int[][] pixelsEffectedByShape;
    private int lineThickness;
    protected int[] startingCoordinate;
    protected int[] endingCoordinate;
    protected int drawStage;

    public Shape(int brushSize) {
        this.lineThickness = 1;
        this.drawStage = 0;
        this.startingCoordinate = new int[2];
        this.endingCoordinate = new int[2];
    }

    /**
     * Get the current line thickness used on the shapes
     *
     * @return      an integer of the thickness of the line
     */
    public int getLineThickness() {
        return lineThickness;
    }

    /**
     * Set the current line thickness used on the shapes
     */
    public void setLineThickness(int lineThickness) {
        this.lineThickness = lineThickness;
    }

    /**
     * Initiated everytime the mouse interact with the canvas, responsible for the output drawn on canvas per each
     * interaction.
     *
     * As a result of the expected functioning of the Shape tool, the draw method is divided to draw different items
     * (the dot on first stage, the actual shape on second stage).
     *
     * @param currentCanvas     The current canvas which the mouse is interacting with
     * @param currentColour     The current color chosen by the user
     * @param x                 The x coordinate of the user mouse input
     * @param y                 The y coordinate of the user mouse input
     * @return                  AN array collection of arrays of integer coordinates around the (x, y) coordinates in
     *                          the shape of pixelsEffectedByBrush
     */
    public int[][] draw(Project currentCanvas, Colour currentColour, int x, int y) {
        // Creating a new array to store the pixels that are updated in this method. These will then
        // Be sent back up to javafx to be rendered on the canvas.
        int[][] pixelsToUpdate = new int[pixelsEffectedByShape.length][2];

        // Initiated when the user first clicked on the canvas
        if (drawStage == 1) {
            // draws the pixelsEffectedByShape which per the calculation of <calculateEffectedPixels> specific for each
            // shape, is going to be a square on the first drawStage
            for (int offset = 0; offset < pixelsEffectedByShape.length; offset++) {
                //Check if the pixels are in the canvas, we have the
                if (x + pixelsEffectedByShape[offset][0] > 0
                        && x + pixelsEffectedByShape[offset][0] < currentCanvas.width
                        && y + pixelsEffectedByShape[offset][1] > 0
                        && y + pixelsEffectedByShape[offset][1] < currentCanvas.height) {
                    //If they are, update the updated pixels list and the canvas itself
                    pixelsToUpdate[offset][0] = x + pixelsEffectedByShape[offset][0];
                    pixelsToUpdate[offset][1] = y + pixelsEffectedByShape[offset][1];
                    currentCanvas.drawingCanvas[x + pixelsEffectedByShape[offset][0]]
                            [y + pixelsEffectedByShape[offset][1]].setRGB(currentColour.getRGB());
                }
            }

            // increment the drawStage by 1 since we have completed updating method pixelsToUpdate to draw out a dot
            drawStage++;

            // store the current (x, y) coordinate into the array of startingCoordinate
            this.startingCoordinate[0] = x;
            this.startingCoordinate[1] = y;

        // Initiated when the user makes the second click on the canvas
        } else if (drawStage == 2) {

            // store the current (x, y) coordinate into the array of endingCoordinate
            this.endingCoordinate[0] = x;
            this.endingCoordinate[1] = y;

            // calls the calculateEffectedPixels method on stage 2 which will return pixelsEffectedByShape to be
            // the specific shape with its exact coordinates we want to plot
            this.calculateEffectedPixels();

            // variable <pixel> is getting the exact amount of pixels we have to plot in the pixelsEffectedByShape
            // array
            for (int pixel = 0; pixel < pixelsEffectedByShape.length; pixel++) {
                // Check if the pixels are in the canvas, the calculation done in calculateEffectedPixels already
                // computed the exact coordinates
                if (pixelsEffectedByShape[pixel][0] > 0  // x > 0
                        && pixelsEffectedByShape[pixel][0] < currentCanvas.width  // x < width of canvas
                        && pixelsEffectedByShape[pixel][1] > 0  // y > 0
                        && pixelsEffectedByShape[pixel][1] < currentCanvas.height) {  // y < width of canvas

                    //If they are, update the updated pixels list and the canvas itself
                    pixelsToUpdate[pixel][0] = pixelsEffectedByShape[pixel][0];
                    pixelsToUpdate[pixel][1] = pixelsEffectedByShape[pixel][1];
                    currentCanvas.drawingCanvas[pixelsEffectedByShape[pixel][0]]
                            [pixelsEffectedByShape[pixel][1]].setRGB(currentColour.getRGB());
                }

            }

            // reset the drawStage to 0 as we have completed updating method pixelsToUpdate to draw out the shape, the
            // user can now redo the entire process over again
            drawStage = 0;
            // initiate the calculateEffectedPixels for stage 1 of dot drawing
            this.calculateEffectedPixels();
        }
        // return the array of coordinates as desired which will be drawn by javaFx onto the canvas
        return pixelsToUpdate;
    }

    /**
     *  Calculates the distance between the starting coordinate and the ending coordinate by x and y
     *
     * @return      returns the x and y distance between the two coordinates as an array of (distance x, distance y)
     */
    public int[] calculateStartEndDistance() {
        int[] distances = new int[2];
        // the x distance between the two pixels
        int xDistance = startingCoordinate[0] - endingCoordinate[0];
        // the y distance between the two pixels
        int yDistance = startingCoordinate[1] - endingCoordinate[1];

        distances[0] = xDistance;
        distances[1] = yDistance;

        return distances;
    }
}
