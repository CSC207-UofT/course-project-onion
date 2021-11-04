package com.onionshop.entities;

public abstract class Brush implements Tool {
    private String brushShape;
    private int brushSize;
    protected int[][] pixelsEffectedByBrush;

    /**
     * Construct a Brush that has the properties brush shape and brush size.
     *
     * @param brushShape    String of the shape of the brush, all lower case
     * @param brushSize     int of the size of the brush, represents pixel size
     */
    public Brush(String brushShape, int brushSize) {
        this.brushShape = brushShape;
        this.brushSize = 1;
    }

    /**
     * Returns the current SHAPE of the brush.
     *
     * @return      the current shape of the brush in String
     */
    public String getBrushShape() {
        return brushShape;
    }

    /**
     * Returns the current SIZE of the brush.
     *
     * @return      the current size of the brush in int
     */
    public int getBrushSize() {
        return brushSize;
    }

    /**
     * Sets the current SHAPE of the brush to an input string brushShape.
     *
     * @param brushShape    String of the shape of the brush, all lower case
     */
    public void setBrushShape(String brushShape) {
        this.brushShape = brushShape;
    }

    /**
     * Sets the current SIZE of the brush to an input string brushSize.
     *
     * @param brushSize     String of the size of the brush, represents pixel size
     */
    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }

    public int[][] draw(Project currentCanvas, Colour currentColour, int x, int y) {
        // Creating a new array to store the pixels that are updated in this method. These will then
        // Be sent back up to javafx to be rendered on the canvas.
        int[][] pixelsToUpdate = new int[pixelsEffectedByBrush.length][2];

        for (int offset = 0; offset < pixelsEffectedByBrush.length; offset++) {
            //Check if the pixels are in the canvas
            if (x + pixelsEffectedByBrush[offset][0] > 0 && x + pixelsEffectedByBrush[offset][0] < currentCanvas.width
                    && y + pixelsEffectedByBrush[offset][1] > 0 &&
                    y + pixelsEffectedByBrush[offset][1] < currentCanvas.height) {
                //If they are, update the updated pixels list and the canvas itself

                pixelsToUpdate[offset][0] = x + pixelsEffectedByBrush[offset][0];
                pixelsToUpdate[offset][1] = y + pixelsEffectedByBrush[offset][1];
                currentCanvas.drawingCanvas[x + pixelsEffectedByBrush[offset][0]]
                        [y + pixelsEffectedByBrush[offset][1]].setRGB(currentColour.getRGB());
            }
        }

        return pixelsToUpdate;
    }

    public void calculateEffectedPixels() {
        pixelsEffectedByBrush = new int[121][2];

        int counter = 0;
        for (int x = -5; x < 6; x++) {
            for (int y = -5; y < 6; y++) {
                this.pixelsEffectedByBrush[counter][0] = x;
                this.pixelsEffectedByBrush[counter][1] = y;
                counter++;
            }
        }
    }

}
