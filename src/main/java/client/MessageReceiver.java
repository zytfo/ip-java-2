package client;

import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;


public class MessageReceiver implements Receiver {

    private final static Logger logger = Logger.getLogger(MessageReceiver.class);
    private MessageListener MessageListener;
    private final DataInputStream inputStream;

    MessageReceiver(DataInputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void startReceiving() {
        Runnable runnable = () -> {
            String message;
            while (ClientMain.work) {
                try {
                    message = inputStream.readUTF();
                    MessageListener.onNewMessage(message);
                } catch (IOException e) {
                    logger.error("Connection is closed.", e);
                    stopReceiving();
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
        ClientMain.work = false;
        try {
            inputStream.close();
        } catch (IOException e) {
            logger.error("Some problem with closing stream has occurred.", e);
        }
    }

}
