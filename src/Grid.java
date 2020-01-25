import java.util.ArrayList;

import processing.core.PApplet;

public class Grid {
    private static final float FULL_GRID_WIDTH = 3200;
    private static final int NUM_FOODS = 250;
    private static final float INITIAL_SQUARE_SIZE = 60;
    private static final float SCALE_FACTOR = 0.99f;

    private PApplet screen;
    private float squareSize;
    private float screenX, screenY;

    private ArrayList<Food> foods;
    private float speed;

    public Grid(PApplet w) {
        this.screen = w;
        this.squareSize = INITIAL_SQUARE_SIZE;

        this.screenX = 600;
        this.screenY = 600;
        this.speed = 3;

        foods = new ArrayList<>();

        for (int i = 0; i < NUM_FOODS; i++) {
            float randomX = (float) (Math.random() * FULL_GRID_WIDTH);
            float randomY = (float) (Math.random() * FULL_GRID_WIDTH);

            foods.add(new Food(screen, randomX, randomY));

        }

    }

    public void draw() {
        screen.stroke(screen.color(200, 200, 200));

        for (int i = 0; i < FULL_GRID_WIDTH; i += getSquareSize()) {
            // horizontal
            screen.line(getLeft(), i - screenY, getRight(), i - screenY);

            // vertical
            screen.line(i - screenX, getTop(), i - screenX, getBottom());

        }

        for (Food f : foods) {
            f.draw(f.getX() - screenX, f.getY() - screenY);
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



    public void eatAndReplace(int i) {
        foods.remove(i);
        float randomX = (float) (Math.random() * FULL_GRID_WIDTH);
        float randomY = (float) (Math.random() * FULL_GRID_WIDTH);

        foods.add(new Food(screen, randomX, randomY));

    }

    public void decreaseSpeed() {
        speed *= SCALE_FACTOR;
    }

    // getters

    public Food getFood(int i) {
        return foods.get(i);
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

    public int foodNum() {
        return foods.size();
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

}
