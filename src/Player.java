import processing.core.PApplet;

import javax.swing.*;

public class Player extends GameObject{
    private static final float DEFAULT_START_SIZE = 120;
    private static final float DEFAULT_START_SPEED = 0.1f;
    private static final String SEPARATOR = "Ѭ";

    private float speed;
    private float size;
    private int color;
    private Grid grid;
    private int counter;
    private boolean alive;
    private String name;

    public Player(PApplet w, Grid g, boolean askForName) {
        super(w, w.width / 2f, w.height / 2f);

        this.grid = g;

        this.size = DEFAULT_START_SIZE;
        this.speed = DEFAULT_START_SPEED;

        this.color = randomColor();
        this.counter = 0;
        this.alive = true;

        if (askForName) {
            askForName();
        } else {
            this.name = "";
        }

    }


    public Player(PApplet w, Grid g, String data) {
        this(w, g, false);
        updateData(data);
    }

    private void askForName() {
        this.name = JOptionPane.showInputDialog("Name:");
        while (name.contains(SEPARATOR)) {
            name = JOptionPane.showInputDialog("Name without '" + SEPARATOR + "':");
        }
    }

    public void draw(boolean displayingScore) {
        draw();
        if (displayingScore) displayScore();
    }

    public void draw() {
        if (alive) {
            screen.noStroke();
            screen.fill(color);
            screen.ellipse(x, y, size, size);

            screen.fill(0);
            screen.text(name, x - size / 4, y);

        } else {
            screen.text("dead", x, y);

        }
    }

    private void displayScore() {
        screen.fill(0);
        screen.text((int) size, 10, 20);
    }

    public void followMouse(float x, float y) {
        float xDist = x - this.x;
        float yDist = y - this.y;

        this.x += xDist * speed;
        this.y += yDist * speed;

        restrictToBounds();

    }

    private void restrictToBounds() {
        x = MathStuff.restrictToRange(x, grid.getLeft() + size / 2, grid.getRight() - size / 2);
        y = MathStuff.restrictToRange(y, grid.getTop() + size / 2, grid.getBottom() - size / 2);

    }


    // check if obj is hitting other obj - both x and y overlap
    public boolean isHitting(Grid g, Food f) {
        float foodX = f.getX() - g.getScreenX();
        float foodY = f.getY() - g.getScreenY();

        return MathStuff.getDistance(x, y, foodX, foodY) < size / 2;
    }

    public boolean isHitting(Player p) {
        boolean isBigger = this.size > p.getSize();

        return isBigger && MathStuff.getDistance(x, y, p.getX(), p.getY()) < size / 2;
    }

    public void setAlive(boolean b) {
        alive = b;
    }

    public void increaseSize() {
        size++;
        counter++;

        if (size < 200 && counter > 5 || size >= 200 && counter > 10) {
            grid.decreaseSpeed();
            counter = 0;
        }
    }

    // getters

    public float getXOnGrid() {
        return x + grid.getScreenX();
    }

    public float getYOnGrid() {
        return y + grid.getScreenY();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSize() {
        return size;
    }

    public boolean isAlive() {
        return alive;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    // setters

    public void setName(String name) {
        this.name = name;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getData() {
        return getName() + SEPARATOR
                + getXOnGrid() + SEPARATOR
                + getYOnGrid() + SEPARATOR
                + getSize() + SEPARATOR
                + getColor() + SEPARATOR
                + isAlive();

    }

    public void updateData(String data) {
        String[] p2data = data.split(SEPARATOR);

        setName(p2data[0]);
        setX(Float.parseFloat(p2data[1]) - grid.getScreenX());
        setY(Float.parseFloat(p2data[2]) - grid.getScreenY());
        setSize(Float.parseFloat(p2data[3]));
        setColor(Integer.parseInt(p2data[4]));
        setAlive(Boolean.parseBoolean(p2data[5]));
    }
}
