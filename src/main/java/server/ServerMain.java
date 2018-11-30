package server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {
    private final static Logger logger = Logger.getLogger(Server.class);
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();
        MessageHandler messageHandler = new MessageHandler();
        try {
            ServerSocket socket = new ServerSocket(9000);
            ConnectionHandler connectionHandler = new ConnectionHandler(socket);
            Server server = new Server(messageQueue, connectionHandler, messageHandler);
            server.addChat();
            server.init();
        } catch (IOException e) {
            logger.error("Cannot start server.", e);
        }
    }
}
