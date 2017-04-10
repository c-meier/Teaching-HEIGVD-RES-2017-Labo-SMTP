import java.io.IOException;

/**
 * Created by christopher on 06.04.17.
 */
public interface ISMTPClient {
    void connect(String server, int port) throws IOException;
    void disconnect() throws IOException;
    boolean isConnected();
    void sendMessage(IMessage message) throws IOException;
}
