package client;

import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;


public class MessageReceiver implements Receiver {

    private final static Logger logger = Logger.getLogger(MessageReceiver.class);
    private MessageListener MessageListener;
    private DataInputStream inputStream;
    private volatile boolean work;

    MessageReceiver(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void startReceiving() {
        work = true;
        Runnable runnable = () -> {
            String message;
            while (work) {
                try {
                    message = inputStream.readUTF();
                    MessageListener.onNewMessage(message);
                } catch (IOException e) {
                    logger.error("Connection is closed.", e);
                    System.exit(0);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void setOnNewMessageListener(MessageListener onNewMessageListener) {
        this.MessageListener = onNewMessageListener;
    }

    @Override
    public void stopReceiving() {
        work = false;
        try {
            inputStream.close();
        } catch (IOException e) {
            logger.error("Some problem with closing stream has occurred.", e);
        }
    }

}
