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

            System.out.println("first what " + drawStage);

            drawStage++;

            System.out.println("should be two now what " + drawStage);

            System.out.println("first " + x);
            System.out.println("first " + y);

            this.startingCoordinate[0] = this.startingCoordinate != null ? x : this.startingCoordinate[0];
            this.startingCoordinate[1] = this.startingCoordinate != null ? y : this.startingCoordinate[1];

            System.out.println("first should be x what " + this.startingCoordinate[0]);
            System.out.println("first should be y what " + this.startingCoordinate[1]);

        } else if (drawStage == 2) {

            System.out.println("should still be two now what " + drawStage);

            System.out.println("second " + x);
            System.out.println("second " + y);

            this.endingCoordinate[0] = this.endingCoordinate != null ? x : this.endingCoordinate[0];
            this.endingCoordinate[1] = this.endingCoordinate != null ? y : this.endingCoordinate[1];

            System.out.println("second should be x what " + this.endingCoordinate[0]);
            System.out.println("second should be y what " + this.endingCoordinate[1]);

            // stage 2, return pixelsEffectedByShape to be the line and its exact coordinate we want to plot
            this.calculateEffectedPixels();

            // pixel is getting the exact amount of pixels we have to plot in the pixelsEffectedByShape array
            for (int pixel = 0; pixel < pixelsEffectedByShape.length; pixel++) {

                System.out.println("calculated values doing alright? " + pixelsEffectedByShape[pixel][0]);
                System.out.println("calculated values doing alright? " + pixelsEffectedByShape[pixel][1]);

                // Check if the pixels are in the canvas, we don't need to add pixelsEffectedByShape onto x or y since
                // the calculation done in calculateEffectedPixels already computed the exact coordinates
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

    public double calculateSlope(int[] distances) {
        // the slope is rise/run
        double slope = (double) distances[1] / distances[0];

        return slope;
    }

}
