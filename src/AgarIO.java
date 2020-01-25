import java.io.IOException;

import processing.core.PApplet;

public class AgarIO extends PApplet {
    private Player p1, p2;
    private Grid grid;
    private ServerConnection server;

    public void setup() {
        size(1920, 950);
        grid = new Grid(this);

        p1 = new Player(this, grid, true);

        try {
            server = new Server();
        } catch (IOException e) {
            System.out.println("SERVER already exists, making CLIENT");

            try {
                server = new Client();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void draw() {
        background(255);

        try {
            server.sendData(p1.getData());
            updateP2(server.readData());

        } catch (IOException e) {
            e.printStackTrace();
        }

        grid.draw();

        if (p1.isAlive()) {
            p1.followMouse(mouseX, mouseY);
            grid.follow(mouseX, mouseY);
        }

        // check for food collision
        for (int i = 0; i < grid.foodNum(); i++) {
            if (p1.isHitting(grid, grid.getFood(i))) {
                grid.eatAndReplace(i);
                i--;
                p1.increaseSize();
            }
        }

        p1.draw(true);
        p2.draw(false);

    }

    private void updateP2(String data) {
        if (p2 == null) {
            p2 = new Player(this, grid, data);
            return;
        }

        p2.updateData(data);
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{"AgarIO"});
    }
}

