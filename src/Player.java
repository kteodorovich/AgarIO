import processing.core.PApplet;

import javax.swing.*;

public class Player extends GameObject {
    private static final float DEFAULT_START_SIZE = 120;
    private static final float DEFAULT_START_SPEED = 0.1f;
    private static final String SEPARATOR = "Ѭ";

    private float speed;
    private float diameter;
    private int color;
    private int score;
    private boolean alive;
    private String name;

    public Player(PApplet w, Grid g, boolean askForName) {
        super(w, g, w.width / 2f, w.height / 2f);

        this.diameter = DEFAULT_START_SIZE;
        this.speed = DEFAULT_START_SPEED;

        this.color = randomColor();
        this.score = 0;
        this.alive = true;

        if (askForName) {
            askForName();
        } else {
            name = "";
        }
    }


    public Player(PApplet w, Grid g, String data) {
        this(w, g, false);
        updateData(data);
    }

    private void askForName() {
        this.name = JOptionPane.showInputDialog("Name:");

        while (name.contains(SEPARATOR)) {
            name = JOptionPane.showInputDialog("Cannot use '" + SEPARATOR + "' in name. Try again:");
        }
    }

    public void draw() {
        if (alive) {
            screen.noStroke();
            screen.fill(color);
            screen.ellipse(getXOnScreen(), getYOnScreen(), diameter, diameter);

            screen.fill(0);
            screen.text(name, getXOnScreen() - diameter / 4, getYOnScreen());

        } else {
            screen.text("dead", getXOnScreen(), getYOnScreen());

        }
    }

    public void displayScore() {
        screen.fill(0);
        screen.text((int) diameter, 10, 20);
    }

    public void followMouse(float x, float y) {
        float xDist = x - this.getXOnScreen();
        float yDist = y - this.getYOnScreen();

        this.absX += xDist * speed;
        this.absY += yDist * speed;

        restrictToBounds();

    }

    private void restrictToBounds() {
        absX = MathStuff.restrictToRange(absX, getDiameter() / 2, (float) grid.getFullFieldWidth() - getDiameter() / 2);
        absY = MathStuff.restrictToRange(absY, getDiameter() / 2, (float) grid.getFullFieldWidth() - getDiameter() / 2);

    }


    // check if obj is hitting other obj - both x and y overlap
    public boolean isHitting(Grid g, Food f) {
        return MathStuff.getDistance(absX, absY, f.getAbsoluteX(), f.getAbsoluteY()) < diameter / 2;
    }

    public boolean isHitting(Player p) {
        return MathStuff.getDistance(absX, absY, p.getAbsoluteX(), p.getAbsoluteY()) < diameter / 2;
    }

    public void setAlive(boolean b) {
        alive = b;
    }

    public void increaseSize() {
        diameter++;
        score++;

        if (diameter < 200 && score > 5 || diameter >= 200 && score > 10) {
            score = 0;
        }
    }

    // getters

    public float getDiameter() {
        return diameter;
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

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getData() {
        return getName() + SEPARATOR
                + getAbsoluteX() + SEPARATOR
                + getAbsoluteY() + SEPARATOR
                + getDiameter() + SEPARATOR
                + getColor() + SEPARATOR
                + isAlive();

    }

    public void updateData(String data) {
        String[] p2data = data.split(SEPARATOR);

        setName(p2data[0]);
        setAbsoluteX(Float.parseFloat(p2data[1]));
        setAbsoluteY(Float.parseFloat(p2data[2]));
        setDiameter(Float.parseFloat(p2data[3]));
        setColor(Integer.parseInt(p2data[4]));
        setAlive(Boolean.parseBoolean(p2data[5]));
    }
}
