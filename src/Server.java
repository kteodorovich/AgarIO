import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends ServerConnection {
    private ServerSocket listener;

    public Server() throws IOException {
        listener = new ServerSocket(SERVER_PORT);
        System.out.println("[SERVER] Waiting for client connection...");

        Socket client = listener.accept();
        System.out.println("[SERVER] Connected to client!");

        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }


}

