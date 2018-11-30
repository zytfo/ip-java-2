package server;

import java.net.Socket;

public interface ConnectionListener {
    void addConnection(Socket socket);
}
