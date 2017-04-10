/**
 * Created by christopher on 06.04.17.
 */
public class PrankMessage implements IMessage {
    private String from;
    private String to;
    private String subject;
    private String content;

    public PrankMessage() {
        this.from = null;
        this.to = null;
        this.subject = null;
        this.content = null;
    }

    public PrankMessage(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
