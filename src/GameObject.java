import processing.core.PApplet;

public abstract class GameObject {
    protected PApplet screen;
    protected Grid grid;
    protected float absX, absY;

    public GameObject(PApplet screen, Grid g, float absX, float absY) {
        this.screen = screen;
        this.grid = g;
        this.absX = absX;
        this.absY = absY;
    }

    public int randomColor() {
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);

        return screen.color(r, g, b);


    }

    public abstract void draw();

    public float getAbsoluteX() {
        return absX;
    }

    public float getXOnScreen() {
        return absX - grid.getScreenX();
    }

    public float getAbsoluteY() {
        return absY;
    }

    public float getYOnScreen() {
        return absY - grid.getScreenY();
    }

    public void setAbsoluteX(float x){
        absX = x;
    }

    public void setAbsoluteY(float y) {
        absY = y;
    }
}
