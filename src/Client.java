import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends ServerConnection {
    private final String serverIp;
    private Socket socket;

    public Client() throws IOException {
        this(DEFAULT_SERVER_IP);
    }

    public Client(String ip) throws IOException {
        serverIp = ip;

        socket = new Socket(serverIp, SERVER_PORT);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }
}


