package server;

import chat.Message;
import org.apache.log4j.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {

    private final static Logger logger = Logger.getLogger(MessageQueue.class);
    private final LinkedBlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private volatile boolean work = true;
    private DataOutputStream outputStream;

    public void sendMessage(Message message) {
        synchronized (queue) {
            queue.add(message);
        }
    }

    public void startSending() {
        work = true;
        Runnable runnable = () -> {
            Message message;
            while (work) {
                try {
                    message = queue.take();
                    logger.info("New message from client side: " + message.getText());
                    try {
                        outputStream = message.getDataOutputStream();
                        outputStream.writeUTF(message.getText());
                        outputStream.flush();
                    } catch (SocketException e) {
                        logger.error("User disconnected.", e);
                    } catch (IOException e) {
                        logger.error("Some problem with stream output has occurred with message: " + message.getText(), e);
                    }
                } catch (InterruptedException e) {
                    logger.error("Message queue was interrupted...", e);
                    startSending();
                    work = false;
                    return;
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public void stopSending() {
        work = false;
        queue.clear();
    }

    public void clear() {
        stopSending();
        try {
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Problems with closing output stream.");
            e.printStackTrace();
        }
    }
}
