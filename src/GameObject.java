import processing.core.PApplet;

public abstract class GameObject {
    protected PApplet screen;
    protected float x, y;

    public GameObject(PApplet screen, float x, float y) {
        this.screen = screen;
        this.x = x;
        this.y = y;
    }

    protected int randomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);

        return screen.color(r, g, b);


    }

    public abstract void draw();
}
