package client;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class InputReaderTest {
    @Mock
    Logger logger;
    @Mock
    InputStream inputStream;
    @Mock
    MessageSender messageQueue;
    @Mock
    Socket socket;
    @InjectMocks
    InputReader inputReader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartReader() throws Exception {
        inputReader.startReader();
    }
}