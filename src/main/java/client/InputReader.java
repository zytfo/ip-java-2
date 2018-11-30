package client;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class InputReader {

    private final static Logger logger = Logger.getLogger(InputReader.class);
    private InputStream inputStream;
    private MessageSender messageQueue;
    private volatile boolean work = true;

    InputReader(InputStream inputStream, MessageSender messageQueue) {
        this.inputStream = inputStream;
        this.messageQueue = messageQueue;
    }

    public void startReading() {
        messageQueue.start();
        Runnable runnable = () -> {
            Scanner scanner = new Scanner(inputStream);
            String input;
            while (work) {
                input = scanner.nextLine();
                if (input.equals("/quit")) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        logger.error("An error in reader has occurred.", e);
                    }
                    messageQueue.clear();
                    System.exit(0);
                }
                messageQueue.sendMessage(input);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void stop() {
        work = false;
        messageQueue.stop();
    }
}
