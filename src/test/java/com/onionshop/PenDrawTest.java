package com.onionshop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PenDrawTest {

    @Test
    void draw() {
        Project testPenProject = new Project("TestPath", 200, 200);
        Colour testColour = new Colour("Red", new int[]{255, 0, 0});
        Pen testPen = new Pen("round", 1);

        int[][] testPixelsAffected = testPen.draw(testPenProject, testColour, 0, 1);

        assertTrue(testPixelsAffected.length == 4 &&
                testPixelsAffected[0][0] == 0 && testPixelsAffected[0][1] == 2
                && testPixelsAffected[1][0] == 1 && testPixelsAffected[1][1] == 1
                && testPixelsAffected[2][0] == 0 && testPixelsAffected[2][1] == 0
                && testPixelsAffected[3][0] == 0 && testPixelsAffected[3][1] == 1);
    }
}