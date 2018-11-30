package chat;

import java.io.DataOutputStream;

public class Message {
    private String text;
    private DataOutputStream outputStream;

    public Message(String text, DataOutputStream outputStream) {
        this.text = text;
        this.outputStream = outputStream;
    }

    public String getText() {
        return text;
    }

    public DataOutputStream getDataOutputStream() {
        return outputStream;
    }
}
