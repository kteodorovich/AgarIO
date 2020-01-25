import java.io.IOException;

public interface ServerConnection {
    void sendData(String data);
    String readData() throws IOException;
}
