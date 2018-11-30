package client;

public class Client {

    private final MessageReceiver messageReceiver;
    private final InputReader inputReader;

    Client(MessageReceiver messageReceiver, InputReader inputReader) {
        this.messageReceiver = messageReceiver;
        this.inputReader = inputReader;
    }

    public void init() {
        messageReceiver.setOnNewMessageListener(System.out::println);
        inputReader.startReading();
        messageReceiver.startReceiving();
    }
}
