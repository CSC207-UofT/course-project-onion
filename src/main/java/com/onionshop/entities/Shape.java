package com.onionshop.entities;

public abstract class Shape implements Tool {

    /**
     * The Shape class is an abstract class where individual shapes extends from.
     * The actual design and implementation of the Shape class and its interaction with its child classes will be
     * explained below:
     *
     * === Expectation of the function of the Shape tool on canvas ===
     * 1. User clicks on canvas without releasing the mouse
     * 2. User drags the cursor to anywere on the canvas and release the mouse, a shape is drawn between the click
     * and the release
     *
     * === How it's implemented ===
     * startingCoordinate       the first coordinates which the user clicked on
     * endingCoordinate         the second coordinates which the user released their mouse on
     * drawStage                a variable that keeps track of which stage of the shape tool drawing process is
     *                          currently active
     *
     * === drawStage and each stages' purpose ===

     * When drawStage = 1
     *      This is the stage where the user presses their mouse down for the start stage. The start coordinate is
     *      recorded but nothing is displayed yet
     *
     * When drawStage = 2
     *      This is the stage where the canvas is waiting for the user to release their mouse. Nothing is displayed yet
     *
     * When drawStage = 3
     *      This is the stage when the user releases their mouse. At this point, the end coordinate is recorded and the
     *      shape is calculated and displayed based on where the user released their mouse
     */

    protected int[][] pixelsEffectedByShape;
    private int lineThickness;
    protected int[] startingCoordinate;
    protected int[] endingCoordinate;
    protected int drawStage;

    public Shape(int brushSize) {
        this.lineThickness = 1;
        this.drawStage = 1;
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
     * Set the Starting Coordinate of the shape for testing purposes
     */
    public void setStartingCoordinate(int[] startingCoordinate) {
        this.startingCoordinate = startingCoordinate;
    }

    /**
     * Set the Ending Coordinate of the shape for testing purposes
     */
    public void setEndingCoordinate(int[] endingCoordinate) {
        this.endingCoordinate = endingCoordinate;
    }

    /**
     * Get the Starting Coordinate of the shape for testing purposes
     *
     * @return      the starting coordinate
     */
    public int[] getStartingCoordinate() {
        return startingCoordinate;
    }

    /**
     * Get the Ending Coordinate of the shape for testing purposes
     *
     * @return      the ending coordinate
     */
    public int[] getEndingCoordinate() {
        return endingCoordinate;
    }

    public void incrementDrawingStage() {drawStage++;}

    public int getDrawStage() {return drawStage;}

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

        // Initiated when the user first clicks on the canvas
        if (drawStage == 1) {
            pixelsToUpdate = new int[0][0];

            // increment the drawStage by 1 since we have completed updating method pixelsToUpdate to draw out a dot
            drawStage++;

            // store the current (x, y) coordinate into the array of startingCoordinate
            this.startingCoordinate[0] = x;
            this.startingCoordinate[1] = y;

        }
        else if (drawStage == 2) {
            //This draw stage returns an empty array while waiting for the user to release their mouse
            //which will draw the shape in draw stage 3
            pixelsToUpdate = new int[0][0];
        }
        else if (drawStage == 3) {
            //This stage is triggered when the user releases their mouse

            // store the current (x, y) coordinate into the array of endingCoordinate
            this.endingCoordinate[0] = x;
            this.endingCoordinate[1] = y;

            // calls the calculateEffectedPixels method on stage 2 which will return pixelsEffectedByShape to be
            // the specific shape with its exact coordinates we want to plot
            this.calculateEffectedPixels();

            pixelsToUpdate = new int[pixelsEffectedByShape.length][2];

            // variable <pixel> is getting the exact amount of pixels we have to plot in the pixelsEffectedByShape
            // array
            updatePixelsOnCanvas(currentCanvas, currentColour, pixelsToUpdate, pixelsEffectedByShape);

            // reset the drawStage to 1 as we have completed updating method pixelsToUpdate to draw out the shape, the
            // user can now redo the entire process over again
            drawStage = 1;
        }
        // return the array of coordinates as desired which will be drawn by javaFx onto the canvas
        return pixelsToUpdate;
    }

    /**
     * This function takes in the list of pixels to be updated based on where the user clicked and
     * released and then updates those pixels on the backend canvas, while also modifying the list of
     * pixels that will be sent to the frontend canvas to update for the user.
     *
     * @param currentCanvas The current project the user is drawing on
     * @param currentColour The current colour the user is using
     * @param pixelsToUpdate The list of pixels that are modified on the backend (will not include pixels outside the
     *                       canvas
     * @param pixelsEffectedByShape The calculated list of pixels that should be modified based on where the user
     *                              released their mouse (may include pixels that are outside the canvas)
     */
    private void updatePixelsOnCanvas(Project currentCanvas, Colour currentColour,
                                        int[][] pixelsToUpdate, int[][] pixelsEffectedByShape) {
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
