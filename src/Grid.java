import java.util.ArrayList;

import processing.core.PApplet;

public class Grid {
    private static final float FULL_GRID_WIDTH = 3200;
    private static final float INITIAL_SQUARE_SIZE = 50;

    private PApplet screen;
    private float squareSize;
    private float screenX, screenY;

    private float speed;

    public Grid(PApplet w) {
        this.screen = w;
        this.squareSize = INITIAL_SQUARE_SIZE;

        this.screenX = 600;
        this.screenY = 600;
        this.speed = 3;

    }

    public void draw() {
        screen.stroke(screen.color(200, 200, 200));

        for (int i = 0; i < FULL_GRID_WIDTH; i += getSquareSize()) {
            // horizontal
            screen.line(getLeft(), i - screenY, getRight(), i - screenY);

            // vertical
            screen.line(i - screenX, getTop(), i - screenX, getBottom());

        }

    }

    public void follow(float x, float y) {
        // left
        if (x < screen.width / 2f) {
            screenX -= speed;
        }

        // right
        if (x > screen.width / 2f) {
            screenX += speed;
        }

        // up
        if (y < screen.height / 2f) {
            screenY -= speed;
        }

        // right
        if (y > screen.height / 2f) {
            screenY += speed;
        }
        restrictToBounds();

    }

    private void restrictToBounds() {
        screenX = MathStuff.restrictToRange(screenX, -100, FULL_GRID_WIDTH - (screen.width - 100));
        screenY = MathStuff.restrictToRange(screenY, -100, FULL_GRID_WIDTH - 800);
    }


    public float getLeft() {
        return -screenX;
    }

    public float getRight() {
        return FULL_GRID_WIDTH - screenX;
    }

    public float getTop() {
        return -screenY;
    }

    public float getBottom() {
        return FULL_GRID_WIDTH - screenY;
    }

    public float getScreenX() {
        return screenX;
    }

    public float getScreenY() {
        return screenY;
    }


    public float getSquareSize() {
        return squareSize;
    }

    /**
     * @return width of entire map
     */
    public double getFullFieldWidth() {
        return FULL_GRID_WIDTH;
    }
}
