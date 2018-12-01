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

    InputReader(InputStream inputStream, MessageSender messageQueue, Socket socket) {
        this.inputStream = inputStream;
        this.messageQueue = messageQueue;
        this.socket = socket;
    }

    public void startReader() {
        messageQueue.startSending();
        InputReaderWorker inputReaderWorker = new InputReaderWorker();
        inputReaderWorker.start();
    }

    public class InputReaderWorker extends Thread {
        @Override
        public void run() {
            Scanner scanner = new Scanner(inputStream);
            String input;
            while (Client.work) {
                input = scanner.nextLine();
                if (input.equals("/disconnect")) {
                    stopReader();
                    try {
                        inputStream.close();
                        socket.close();
                    } catch (IOException e) {
                        logger.error("An error in reader has occurred.", e);
                    }
                    messageQueue.clear();
                }
                messageQueue.sendMessage(input);
            }
        }

        private void stopReader() {
            Client.work = false;
            messageQueue.stopSending();
        }
    }
}
