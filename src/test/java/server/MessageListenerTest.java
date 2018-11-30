package server;

import chat.Chat;
import chat.User;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import static org.mockito.Mockito.*;

public class MessageListenerTest {
    @Mock
    Logger logger;
    @Mock
    MessageQueue messageQueue;
    @Mock
    HashMap<Integer, Chat> chats;
    @Mock
    ArrayList<String> customCommands;
    @Mock
    ExecutorService pool;
    @Mock
    ArrayList<Class> classes;
    @InjectMocks
    MessageListener messageListener;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendNewMessage() throws Exception {
        messageListener.sendNewMessage(new User(new Socket()), "text");
    }
}