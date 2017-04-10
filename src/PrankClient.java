import sun.rmi.runtime.Log;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * Created by christopher on 06.04.17.
 */
public class PrankClient implements ISMTPClient {
    private static final Logger LOG = Logger.getLogger(PrankClient.class.getName());

    private String hostname;

    private Socket clientSocket;
    PrintWriter os;
    BufferedReader is;

    public PrankClient(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public void connect(String server, int port) throws IOException {
        String response;

        clientSocket = new Socket(server, port);
        os = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Read welcome message
        readResponse(SMTPProtocol.CODE_WELCOME);

        sendLine(SMTPProtocol.CMD_EHLO + hostname);
        readResponse(SMTPProtocol.CODE_OK);
    }

    @Override
    public void disconnect() throws IOException {
        sendLine(SMTPProtocol.CMD_QUIT);
        readResponse(SMTPProtocol.CODE_CONNECTION_CLOSING);

        os.close();
        is.close();
        clientSocket.close();
    }

    @Override
    public boolean isConnected() {
        return !clientSocket.isClosed();
    }

    @Override
    public void sendMessage(IMessage message) throws IOException {
        sendLine(SMTPProtocol.CMD_MAIL_FROM + message.getFrom());
        readResponse(SMTPProtocol.CODE_OK);

        sendLine(SMTPProtocol.CMD_RCPT_TO + message.getTo());
        readResponse(SMTPProtocol.CODE_OK);

        sendLine(SMTPProtocol.CMD_DATA_START);
        readResponse(SMTPProtocol.CODE_DATA_START);

        sendLine(SMTPProtocol.CMD_DATA_FROM + message.getFrom());
        sendLine(SMTPProtocol.CMD_DATA_TO + message.getTo());
        sendLine(SMTPProtocol.CMD_DATA_SUBJECT + message.getSubject());
        sendLine(SMTPProtocol.CMD_CHARSET);
        sendLine(SMTPProtocol.END_LINE + message.getContent());
        sendLine(SMTPProtocol.CMD_DATA_END);
        readResponse(SMTPProtocol.CODE_OK);
    }

    private void sendLine(String s) {
        os.print(s + SMTPProtocol.END_LINE);
        LOG.log(Level.INFO, s);
        os.flush();
    }

    private void readResponse(int validCode) throws IOException {
        String response;
        Pattern p = Pattern.compile("^\\d{3} .*$");

        do {
            response = is.readLine();
            LOG.log(Level.INFO, response);
        } while(!p.matcher(response).matches());

        if(Integer.parseInt(response.substring(0,3)) != validCode) {
            throw new IOException("Invalid server response");
        }
    }
}
