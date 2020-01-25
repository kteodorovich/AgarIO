import java.awt.*;
import java.io.IOException;

import processing.core.PApplet;

import javax.swing.*;

public class AgarIO extends PApplet {
    private Game game;
    private ServerConnection connection;

    public void setup() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        size(screenSize.width, screenSize.height);

        try {
            chooseServerOrClient();

        } catch (IOException e) {
            e.printStackTrace();
        }

        game = new Game(this);
    }

    private void chooseServerOrClient() throws IOException {
        String[] options = new String[]{"Server", "Client"};
        int response = JOptionPane.showOptionDialog(null, "Which game would you like?", "AgarIO",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (response == 0) {
            connection = new Server();
        } else {
            String ip = JOptionPane.showInputDialog("IP:");
            connection = new Client(ip);
        }

    }

    public void draw() {

        try {
            connection.sendData(game.getLocalPlayerData());
            game.updateRemotePlayer(connection.readData());

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

