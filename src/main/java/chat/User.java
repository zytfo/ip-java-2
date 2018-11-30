package chat;

import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class User {

    private final static Logger logger = Logger.getLogger(User.class);
    private Socket socket;
    private String login;
    private int chatID = -1;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;

    public User(Socket userSocket) {
        this.socket = userSocket;
    }

    public int getChatID() {
        return chatID;
    }

    public void setChatID(int chatID) {
        this.chatID = chatID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void closeConnection() {
        outputStream = null;
        inputStream = null;
        try {
            socket.close();
        } catch (IOException e) {
            logger.error("Cannot close connection with user " + getLogin(), e);
        }
    }

    public DataInputStream getDataInputStream() {
        if (inputStream == null) {
            try {
                inputStream = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                return null;
            }
        }
        return inputStream;
    }

    public DataOutputStream getDataOutputStream() {
        if (outputStream == null) {
            try {
                outputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return outputStream;
    }
}
