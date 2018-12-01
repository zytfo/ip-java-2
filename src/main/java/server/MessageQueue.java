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
        MessageQueueWorker messageQueueWorker = new MessageQueueWorker();
        messageQueueWorker.start();
    }

    private void stopSending() {
        work = false;
        queue.clear();
        try {
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Problems with closing output stream.");
            e.printStackTrace();
        }
    }

    public class MessageQueueWorker extends Thread {
        @Override
        public void run() {
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
                        stopSending();
                    } catch (IOException e) {
                        logger.error("Some problem with stream output has occurred with message: " + message.getText(), e);
                        stopSending();
                    }
                } catch (InterruptedException e) {
                    logger.error("Message queue was interrupted...", e);
                    stopSending();
                    return;
                }
            }
        }
    }
}
