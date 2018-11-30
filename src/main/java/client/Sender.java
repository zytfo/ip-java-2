package client;

public interface Sender {
    void sendMessage(String message);

    void start();

    void stop();

    void clear();
}
