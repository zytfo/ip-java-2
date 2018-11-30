package client;

import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    private final static Logger logger = Logger.getLogger(Client.class);
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 9000);
            MessageReceiver messageReceiver = new MessageReceiver(new DataInputStream(socket.getInputStream()));
            MessageSender messageSender = new MessageSender(new DataOutputStream(socket.getOutputStream()));
            InputReader inputReader = new InputReader(System.in, messageSender, socket);
            Client client = new Client(messageReceiver, inputReader);
            client.init();
        } catch (IOException e) {
            logger.error("Looks like server is unavailable.", e);
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

}
