import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements ServerConnection {
    private static final int PORT = 9090;
    private ServerSocket listener;
    private PrintWriter out;
    private BufferedReader in;

    public Server() throws IOException {
        listener = new ServerSocket(PORT);
        System.out.println("[SERVER] Waiting for client connection...");

        Socket client = listener.accept();
        System.out.println("[SERVER] Connected to client!");

        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public String readData() throws IOException {
        String msg = in.readLine();
        System.out.println(msg);
//		return msg.split("/");
        return msg;

    }

    public void sendData(String data) {
        out.println(data);
    }
}

