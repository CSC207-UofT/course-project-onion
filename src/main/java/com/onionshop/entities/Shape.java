package com.onionshop.entities;

public abstract class Shape implements Tool {
    protected int[][] pixelsEffectedByShape;
    private int brushSize;
    protected int[] startingCoordinate;
    protected int[] endingCoordinate;
    protected int drawStage;

    public Shape(int brushSize) {
        this.brushSize = 1;
        this.drawStage = 0;
        this.startingCoordinate = new int[2];
        this.endingCoordinate = new int[2];
    }

    public int getBrushSize() {
        return brushSize;
    }

    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }

    public int[][] draw(Project currentCanvas, Colour currentColour, int x, int y) {
        // Creating a new array to store the pixels that are updated in this method. These will then
        // Be sent back up to javafx to be rendered on the canvas.
        int[][] pixelsToUpdate = new int[pixelsEffectedByShape.length][2];

        if (drawStage == 1) {

            Colour firstCoordinateColour = new Colour("red", new int[]{255, 0, 0});

            for (int offset = 0; offset < pixelsEffectedByShape.length; offset++) {
                //Check if the pixels are in the canvas
                if (x + pixelsEffectedByShape[offset][0] > 0 && x + pixelsEffectedByShape[offset][0] < currentCanvas.width
                        && y + pixelsEffectedByShape[offset][1] > 0 &&
                        y + pixelsEffectedByShape[offset][1] < currentCanvas.height) {
                    //If they are, update the updated pixels list and the canvas itself

                    pixelsToUpdate[offset][0] = x + pixelsEffectedByShape[offset][0];
                    pixelsToUpdate[offset][1] = y + pixelsEffectedByShape[offset][1];
                    currentCanvas.drawingCanvas[x + pixelsEffectedByShape[offset][0]]
                            [y + pixelsEffectedByShape[offset][1]].setRGB(firstCoordinateColour.getRGB());
                }
            }
            drawStage++;

            this.startingCoordinate[0] = this.startingCoordinate == null ? x : this.startingCoordinate[0];
            this.startingCoordinate[1] = this.startingCoordinate == null ? y : this.startingCoordinate[1];

        } else if (drawStage == 2) {
            this.endingCoordinate[0] = this.endingCoordinate == null ? x : this.endingCoordinate[0];
            this.endingCoordinate[1] = this.endingCoordinate == null ? y : this.endingCoordinate[1];

            this.calculateEffectedPixels();

            for (int offset = 0; offset < pixelsEffectedByShape.length; offset++) {
                //Check if the pixels are in the canvas
                if (x + pixelsEffectedByShape[offset][0] > 0 && x + pixelsEffectedByShape[offset][0] < currentCanvas.width
                        && y + pixelsEffectedByShape[offset][1] > 0 &&
                        y + pixelsEffectedByShape[offset][1] < currentCanvas.height) {
                    //If they are, update the updated pixels list and the canvas itself

                    pixelsToUpdate[offset][0] = x + pixelsEffectedByShape[offset][0];
                    pixelsToUpdate[offset][1] = y + pixelsEffectedByShape[offset][1];
                    currentCanvas.drawingCanvas[x + pixelsEffectedByShape[offset][0]]
                            [y + pixelsEffectedByShape[offset][1]].setRGB(currentColour.getRGB());
                }
            }
            drawStage++;
        }

        return pixelsToUpdate;
    }

    /**
     *  Calculates the distance between two pixels
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

    public double calculateSlope() {
        // fixed line thickness for now
        int lineThickness = 1;

        int[] distances = calculateStartEndDistance();

        // the slope is rise/run
        double slope = distances[0] / distances[1];

        return slope;
    }

}
