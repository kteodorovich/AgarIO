import processing.core.PApplet;

import java.util.ArrayList;

public class Game {
    private static final int NUM_FOODS = 250;

    private Player localPlayer, remotePlayer;
    private Grid grid;
    private ArrayList<Food> foods;

    private PApplet screen;

    public Game(PApplet s) {
        screen = s;
        grid = new Grid(screen);
        localPlayer = new Player(screen, grid, true);

        foods = new ArrayList<>();
        for (int i = 0; i < NUM_FOODS; i++) {
            addRandomFood();
        }

    }


    private void addRandomFood() {
        float randomX = (float) (Math.random() * grid.getFullFieldWidth());
        float randomY = (float) (Math.random() * grid.getFullFieldWidth());

        foods.add(new Food(screen, grid, randomX, randomY));
    }

    public void updatePositions(float mouseX, float mouseY) {
        if (localPlayer.isAlive()) {
            localPlayer.followMouse(mouseX, mouseY);
            grid.follow(mouseX, mouseY);
        }

        // check for food collision
        for (int i = 0; i < foods.size(); i++) {
            if (localPlayer.isHitting(grid, foods.get(i))) {
                eatAndReplace(i);
                i--;
                localPlayer.increaseSize();
            }
        }
    }

    public void eatAndReplace(int i) {
        foods.remove(i);
        addRandomFood();

    }

    public void updateRemotePlayer(String data) {
        if (remotePlayer == null) {
            remotePlayer = new Player(screen, grid, data);
            return;
        }

        remotePlayer.updateData(data);
    }

    public String getLocalPlayerData() {
        return localPlayer.getData();
    }

    public void draw() {
        screen.background(255);

        grid.draw();

        for (Food f : foods) {
            f.draw();
        }

        localPlayer.draw();
        localPlayer.displayScore();

        if (remotePlayer != null) remotePlayer.draw();
    }
}
