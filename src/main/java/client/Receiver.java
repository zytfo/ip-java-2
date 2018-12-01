package client;

public interface Receiver {

    void startReceiving();

    void stopReceiving();

    void setOnNewMessageListener(MessageListener onNewMessageListener);
}
