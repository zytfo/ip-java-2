package client;

public class Client {
    static volatile boolean work = true;
    private final MessageReceiver messageReceiver;
    private final InputReader inputReader;

    Client(MessageReceiver messageReceiver, InputReader inputReader) {
        this.messageReceiver = messageReceiver;
        this.inputReader = inputReader;
    }

    public void init() {
        messageReceiver.setOnNewMessageListener(System.out::println);
        inputReader.startReader();
        messageReceiver.startReceiving();
    }
}
