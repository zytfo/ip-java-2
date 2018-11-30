package server;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.ServerSocket;

import static org.mockito.Mockito.*;

public class ConnectionHandlerTest {
    @Mock
    Logger logger;
    @Mock
    ConnectionListener listener;
    @Mock
    ServerSocket serverSocket;
    @InjectMocks
    ConnectionHandler connectionHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartAccepting() throws Exception {
        connectionHandler.startAccepting();
    }
}