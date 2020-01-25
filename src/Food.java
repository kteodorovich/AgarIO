import processing.core.PApplet;

public class Food extends GameObject{
    private static float SIZE = 30;

    private int color;

    public Food(PApplet w, Grid g, float absX, float absY) {
        super(w, g, absX, absY);

        this.color = randomColor();
    }


    public void draw() {
        screen.noStroke();
        screen.fill(color);
        screen.ellipse(getXOnScreen(), getYOnScreen(), SIZE, SIZE);
    }

}

