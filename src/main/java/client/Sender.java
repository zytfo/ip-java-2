package client;

public interface Sender {
    void sendMessage(String message);

    void startSending();

    void stopSending();

    void clear();
}
