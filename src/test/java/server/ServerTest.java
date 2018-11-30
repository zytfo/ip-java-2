package server;

import chat.Chat;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.mockito.Mockito.*;

public class ServerTest {
    @Mock
    Logger logger;
    @Mock
    HashMap<Integer, Chat> chats;
    @Mock
    MessageQueue messageQueue;
    @Mock
    ConnectionHandler connectionHandler;
    @Mock
    MessageHandler messageHandler;
    @InjectMocks
    Server server;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMain() throws Exception {
//        Server.main(new String[]{"args"});
    }
}