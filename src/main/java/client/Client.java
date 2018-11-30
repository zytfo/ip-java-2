package client;

import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Client {

    private MessageReceiver messageReceiver;
    private InputReader inputReader;

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
