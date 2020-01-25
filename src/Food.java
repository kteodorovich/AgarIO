import processing.core.PApplet;

public class Food extends GameObject{
    private static float SIZE = 30;

    private int color;

    public Food(PApplet w, float x, float y) {
        super(w, x, y);

        this.color = randomColor();
    }

    public void draw() {
        draw(getX(), getY());
    }

    public void draw(float x, float y) {
        screen.noStroke();
        screen.fill(color);
        screen.ellipse(x, y, SIZE, SIZE);
    }

    // getters

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}

