package chat;

import java.util.ArrayList;

public class Chat {

    private ArrayList<User> users;

    public Chat() {
        users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
