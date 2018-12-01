package client;

import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    private final static Logger logger = Logger.getLogger(Client.class);
    static volatile boolean work = true;
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9000);
            MessageReceiver messageReceiver = new MessageReceiver(new DataInputStream(socket.getInputStream()));
            MessageSender messageSender = new MessageSender(new DataOutputStream(socket.getOutputStream()));
            InputReader inputReader = new InputReader(System.in, messageSender, socket);
            Client client = new Client(messageReceiver, inputReader);
            client.init();
        } catch (IOException e) {
            logger.error("Looks like server is unavailable.", e);
        }
    }

}
