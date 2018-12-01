package client;

import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;


public class MessageReceiver implements Receiver {

    private final static Logger logger = Logger.getLogger(MessageReceiver.class);
    private final DataInputStream inputStream;
    private MessageListener MessageListener;

    MessageReceiver(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void startReceiving() {
        MessageReceiverWorker messageReceiverWorker = new MessageReceiverWorker();
        messageReceiverWorker.start();
    }

    @Override
    public void setOnNewMessageListener(MessageListener onNewMessageListener) {
        this.MessageListener = onNewMessageListener;
    }

    @Override
    public void stopReceiving() {
        Client.work = false;
        try {
            inputStream.close();
        } catch (IOException e) {
            logger.error("Some problem with closing stream has occurred.", e);
        }
    }

    public class MessageReceiverWorker extends Thread {
        @Override
        public void run() {
            String message;
            while (Client.work) {
                try {
                    message = inputStream.readUTF();
                    MessageListener.onNewMessage(message);
                } catch (IOException e) {
                    logger.error("Connection is closed. Press any key to exit.", e);
                    stopReceiving();
                }
            }
        }
    }
}
