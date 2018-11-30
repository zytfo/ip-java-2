package server;

import chat.User;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MessageHandlerTest {
    @Mock
    Logger logger;
    @Mock
    MessageListener messageListener;
    @InjectMocks
    MessageHandler messageHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartAccepting() throws Exception {
        messageHandler.startAccepting(new User(null));
    }
}