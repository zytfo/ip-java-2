package server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler {
    private final static Logger logger = Logger.getLogger(ConnectionHandler.class);
    private ConnectionListener listener;
    private final ServerSocket serverSocket;
    private volatile boolean accept = true;

    ConnectionHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startAccepting() {
        if (listener == null) {
            return;
        }

        Runnable runnable = () -> {
            while (accept) {
                try {
                    Socket socket = serverSocket.accept();
                    logger.info("Accepted connection from " + socket);
                    listener.addConnection(socket);
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void setConnectionListener(ConnectionListener listener) {
        this.listener = listener;
    }
}
