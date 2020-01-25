import java.io.IOException;

import processing.core.PApplet;

public class AgarIO extends PApplet {
    private Game game;
    private ServerConnection server;

    public void setup() {
        size(1920, 950);

        game = new Game(this);

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

        try {
            server.sendData(game.getLocalPlayerData());
            game.updateRemotePlayer(server.readData());

        } catch (IOException e) {
            e.printStackTrace();
        }

        game.updatePositions(mouseX, mouseY);
        game.draw();

    }


    public static void main(String[] args) {
        PApplet.main(new String[]{"AgarIO"});
    }
}

