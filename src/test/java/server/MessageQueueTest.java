package server;

import chat.Message;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.DataOutputStream;
import java.util.concurrent.LinkedBlockingQueue;

import static org.mockito.Mockito.*;

public class MessageQueueTest {
    @Mock
    Logger logger;
    @Mock
    LinkedBlockingQueue<Message> queue;
    @Mock
    DataOutputStream outputStream;
    @InjectMocks
    MessageQueue messageQueue;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendMessage() throws Exception {
        messageQueue.sendMessage(new Message("text", null));
    }

    @Test
    public void testStartSending() throws Exception {
        messageQueue.startSending();
    }

    @Test
    public void testStopSending() throws Exception {
        messageQueue.stopSending();
    }

    @Test
    public void testClear() throws Exception {
        messageQueue.clear();
    }
}