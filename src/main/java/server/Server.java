package server;

import chat.Chat;
import chat.Message;
import chat.User;
import org.apache.log4j.Logger;

import java.util.HashMap;


public class Server {

    private final static Logger logger = Logger.getLogger(Server.class);
    private final MessageQueue messageQueue;
    private final ConnectionHandler connectionHandler;
    private final MessageHandler messageHandler;
    private HashMap<Integer, Chat> chats = new HashMap<>();
    private volatile int userCounter = 0;
    private volatile int chatCounter = 0;

    Server(MessageQueue messageQueue, ConnectionHandler connectionHandler, MessageHandler messageHandler) {
        this.messageQueue = messageQueue;
        this.connectionHandler = connectionHandler;
        this.messageHandler = messageHandler;
    }

    public void addChat() {
        chats.put(++chatCounter, new Chat());
    }

    public void init() {
        String help = "Commands:\n" +
                "/select <chatID>: select one of the chats\n" +
                "/chats: get list of possible chats\n" +
                "/custom: get list of custom commands\n" +
                "/disconnect: disconnect";
        connectionHandler.setConnectionListener(socket -> {
            logger.info("-------- New connection --------");
            User user = new User(socket);
            user.setLogin("User " + ++userCounter);
            messageQueue.sendMessage(new Message(help, user.getDataOutputStream()));
            messageHandler.startAccepting(user);
        });
        messageHandler.setMessageListener(new MessageListener(messageQueue, chats));
        messageQueue.startSending();
        connectionHandler.startAccepting();
    }
}
