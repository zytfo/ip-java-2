package client;

import org.apache.log4j.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageSender implements Sender {

    private final static Logger logger = Logger.getLogger(MessageSender.class);
    private final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private final DataOutputStream outputStream;

    MessageSender(DataOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void sendMessage(String message) {
        synchronized (queue) {
            queue.add(message);
        }
    }

    @Override
    public void start() {
        Runnable runnable = () -> {
            String message;
            while (ClientMain.work) {
                try {
                    message = queue.take();
                    try {
                        outputStream.writeUTF(message);
                        outputStream.flush();
                    } catch (IOException e) {
                        logger.error(e);
                        clear();
                    }
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void stop() {
        ClientMain.work = false;
        queue.clear();
    }

    @Override
    public void clear() {
        stop();
        try {
            outputStream.close();
        } catch (IOException e) {
            logger.error("Some problem with closing stream has occurred.", e);
        }
    }

}
