package client;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class InputReader {

    private final static Logger logger = Logger.getLogger(InputReader.class);
    private final InputStream inputStream;
    private final MessageSender messageQueue;
    private final Socket socket;
//    private volatile boolean work = true;

    InputReader(InputStream inputStream, MessageSender messageQueue, Socket socket) {
        this.inputStream = inputStream;
        this.messageQueue = messageQueue;
        this.socket = socket;
    }

    public void startReading() {
        messageQueue.start();
        Runnable runnable = () -> {
            Scanner scanner = new Scanner(inputStream);
            String input;
            input = scanner.nextLine();
            if (input.equals("/disconnect")) {
                try {
                    inputStream.close();
                    socket.close();
                } catch (IOException e) {
                    logger.error("An error in reader has occurred.", e);
                }
                messageQueue.clear();
                System.exit(0);
            }
            messageQueue.sendMessage(input);
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
//
//    public void stop() {
//        work = false;
//        messageQueue.stop();
//    }
}
