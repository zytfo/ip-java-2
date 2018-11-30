package client;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.DataInputStream;

import static org.mockito.Mockito.*;

public class MessageReceiverTest {
    @Mock
    Logger logger;
    @Mock
    client.MessageListener MessageListener;
    @Mock
    DataInputStream inputStream;
    @InjectMocks
    MessageReceiver messageReceiver;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartReceiving() throws Exception {
        messageReceiver.startReceiving();
    }

    @Test
    public void testStopReceiving() throws Exception {
        messageReceiver.stopReceiving();
    }
}