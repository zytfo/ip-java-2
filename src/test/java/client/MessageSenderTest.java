package client;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.DataOutputStream;
import java.util.concurrent.LinkedBlockingQueue;

import static org.mockito.Mockito.*;

public class MessageSenderTest {
    @Mock
    Logger logger;
    @Mock
    LinkedBlockingQueue<String> queue;
    @Mock
    DataOutputStream outputStream;
    @InjectMocks
    MessageSender messageSender;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendMessage() throws Exception {
        messageSender.sendMessage("message");
    }

    @Test
    public void testStartSending() throws Exception {
        messageSender.startSending();
    }

    @Test
    public void testStopSending() throws Exception {
        messageSender.stopSending();
    }

    @Test
    public void testClear() throws Exception {
        messageSender.clear();
    }
}