package server;

import chat.User;
import org.apache.log4j.Logger;

import java.io.DataInputStream;
import java.io.IOException;


public class MessageHandler {
    private final static Logger logger = Logger.getLogger(MessageHandler.class);
    private MessageListener messageListener;
    private volatile boolean work = true;

    public void startAccepting(User user) {
        MessageHandlerWorker messageHandlerWorker = new MessageHandlerWorker(user);
        messageHandlerWorker.start();
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    public class MessageHandlerWorker extends Thread {
        private final User user;

        MessageHandlerWorker(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            DataInputStream inputStream = user.getDataInputStream();
            if (inputStream == null) {
                logger.info("Cannot get messages from " + user.getLogin());
            } else {
                logger.info("Message handler started getting messages from " + user.getLogin() + "\n");
            }
            String message;
            while (work) {
                try {
                    message = user.getDataInputStream().readUTF();
                    messageListener.sendNewMessage(user, message);
                } catch (NullPointerException | IOException e) {
                    stopMessageHandlerWorker();
                    user.closeConnection();
                    logger.error("Connection with " + user.getLogin() + " is closed.\n");
                }
            }
        }

        private void stopMessageHandlerWorker() {
            work = false;
        }
    }
}
