package server;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler {
    private final static Logger logger = Logger.getLogger(ConnectionHandler.class);
    private final ServerSocket serverSocket;
    private ConnectionListener listener;
    private volatile boolean work = true;

    ConnectionHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startAccepting() {
        if (listener == null) {
            return;
        }
        ConnectionHandlerWorker connectionHandlerWorker = new ConnectionHandlerWorker();
        connectionHandlerWorker.start();
    }

    public void setConnectionListener(ConnectionListener listener) {
        this.listener = listener;
    }

    public class ConnectionHandlerWorker extends Thread {
        @Override
        public void run() {
            while (work) {
                try {
                    Socket socket = serverSocket.accept();
                    logger.info("Accepted connection from " + socket);
                    listener.addConnection(socket);
                } catch (IOException e) {
                    logger.error(e);
                    stopAcepting();
                }
            }
        }

        private void stopAcepting() {
            work = false;
            try {
                serverSocket.close();
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }

}
