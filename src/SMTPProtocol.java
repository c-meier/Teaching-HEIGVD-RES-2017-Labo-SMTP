/**
 * Created by christopher on 06.04.17.
 */
public class SMTPProtocol {
    static final public int SMTP_PORT = 25;

    static final public String END_LINE = "\r\n";
    static final public String CMD_EHLO = "EHLO ";
    static final public String CMD_MAIL_FROM = "MAIL FROM: ";
    static final public String CMD_RCPT_TO = "RCPT TO: ";
    static final public String CMD_DATA_START = "DATA";
    static final public String CMD_DATA_FROM = "From: ";
    static final public String CMD_DATA_TO = "To: ";
    static final public String CMD_DATA_SUBJECT = "Subject: ";
    static final public String CMD_DATA_END = ".";
    static final public String CMD_QUIT = "quit";
    static final public String CMD_CHARSET = "Content-Type: text/plain; charset=\"UTF-8\"";

    static final public int CODE_OK = 250;
    static final public int CODE_DATA_START = 354;
    static final public int CODE_WELCOME = 220;
    static final public int CODE_CONNECTION_CLOSING = 221;
}
