import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class ServerConnection {
    protected static final String DEFAULT_SERVER_IP = "127.0.0.1";
    protected static final int SERVER_PORT = 9090;

    protected BufferedReader in;
    protected PrintWriter out;

    public void sendData(String data) {
        out.println(data);
    }

    public String readData() throws IOException {
        String msg = in.readLine();
        System.out.println(msg);
//		return msg.split("/");
        return msg;

    }
}
