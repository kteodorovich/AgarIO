import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements ServerConnection{
    private static final String DEFAULT_SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    private final String serverIp;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter out;

    public Client() throws IOException {
//        serverIp = JOptionPane.showInputDialog("Server IP:");
        serverIp = DEFAULT_SERVER_IP;

        socket = new Socket(serverIp, SERVER_PORT);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void sendData(String data) {
        out.println(data);
    }

    @Override
    public String readData() throws IOException {
        String msg = input.readLine();
        System.out.println(msg);
        return msg;
    }
}


