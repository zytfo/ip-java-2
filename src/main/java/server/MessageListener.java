package server;

import chat.Chat;
import chat.Message;
import chat.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MessageListener {
    private final static Logger logger = Logger.getLogger(MessageListener.class);
    private MessageQueue messageQueue;
    private HashMap<Integer, Chat> chats;
    private ArrayList<String> customCommands = new ArrayList<>();
    private final ExecutorService pool = Executors.newFixedThreadPool(1);
    private ArrayList<Class> classes;

    MessageListener(MessageQueue messageQueue, HashMap<Integer, Chat> chats) {
        this.messageQueue = messageQueue;
        this.chats = chats;
        updateCustomCommands();
    }

    private void updateCustomCommands() {
        try {
            classes = JavaClassLoader.getClasses(this.getClass().getClassLoader(), "plugins");
            String c;
            customCommands.clear();
            for (int i = 0; i < classes.size(); i++) {
                c = ":" + classes.get(i).toString().substring(14);
                customCommands.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onDefaultCommand(User user, String text) {
        String help = "Commands:\n" +
                "/select <chatID>: select one of the chats\n" +
                "/chats: get list of possible chats\n" +
                "/custom: get list of custom commands\n" +
                "/disconnect: disconnect";
        if (text.equals("/disconnect")) {
            int chatID = user.getChatID();
            chats.get(chatID).getUsers().remove(user);
            user.closeConnection();
            pool.shutdown();
            sendToChat(chatID, user.getLogin() + " has disconnected");
        } else if (text.equals("/help")) {
            messageQueue.sendMessage(new Message(help, user.getDataOutputStream()));
        } else if (text.equals("/custom")) {
            updateCustomCommands();
            String message = "External plugins:\n";
            for (int i = 0; i < customCommands.size(); i++)
                message += customCommands.get(i) + "\n";
            message += "To call external plugin type ':<command> <argument>'. For example, ':Factorial 5' (select the room before doing that)." + '\n';
            messageQueue.sendMessage(new Message(message, user.getDataOutputStream()));
        } else if (text.equals("/chats")) {
            StringBuilder chatsList = new StringBuilder();
            for (Map.Entry<Integer, Chat> entry : chats.entrySet()) {
                chatsList.append("id: ")
                        .append(entry.getKey())
                        .append('\n');
            }
            messageQueue.sendMessage(new Message(chatsList.toString(), user.getDataOutputStream()));
        } else if (text.matches("/select \\d+")) {
            int chatID = Integer.parseInt(text.split(" ")[1]);
            if (chats.containsKey(chatID)) {
                if (user.getChatID() != -1) {
                    chats.get(user.getChatID()).getUsers().remove(user);
                }
                user.setChatID(chatID);
                chats.get(chatID).addUser(user);
                sendToChat(chatID, user.getLogin() + " has connected.");
            } else {
                messageQueue.sendMessage(new Message("No such chat\n", user.getDataOutputStream()));
            }
        } else {
            messageQueue.sendMessage(new Message("No such command\n", user.getDataOutputStream()));
            messageQueue.sendMessage(new Message(help, user.getDataOutputStream()));
        }

    }

    private void onCustomCommand(User user, String text) {
        String[] arguments = text.split("\\s+");
        if (customCommands.contains(arguments[0])) {
            Future<Object> result = pool.submit(new JavaClassLoader(arguments));
            try {
                String finish = result.get().toString();
                messageQueue.sendMessage(new Message(finish, user.getDataOutputStream()));
            } catch (InterruptedException | ExecutionException ex) {
                logger.error(ex);
            }
        } else
            messageQueue.sendMessage(new Message("No such custom command.", user.getDataOutputStream()));
    }

    public void sendNewMessage(User user, String text) {
        logger.info(user.getLogin() + " says: " + text);
        if (text.charAt(0) == '/') {
            onDefaultCommand(user, text);
        } else if (text.charAt(0) == ':') {
            onCustomCommand(user, text);
        } else {
            if (user.getChatID() == -1) {
                messageQueue.sendMessage(new Message("You must join to the chat to be able send messages.", user.getDataOutputStream()));
            } else {
                sendToClient(user, text);
            }
        }
    }

    private void sendToClient(User sender, String text) {
        for (User userInChat : chats.get(sender.getChatID()).getUsers()) {
            if (!userInChat.getLogin().equals(sender.getLogin())) {
                messageQueue.sendMessage(new Message(sender.getLogin() + ": " + text, userInChat.getDataOutputStream()));
            }
        }
    }

    private void sendToChat(int chatID, String text) {
        for (User userInChat : chats.get(chatID).getUsers()) {
            messageQueue.sendMessage(new Message(text, userInChat.getDataOutputStream()));
        }
    }
}
