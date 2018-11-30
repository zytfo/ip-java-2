package client;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;

import static org.mockito.Mockito.*;

public class InputReaderTest {
    @Mock
    Logger logger;
    @Mock
    InputStream inputStream;
    @Mock
    MessageSender messageQueue;
    @InjectMocks
    InputReader inputReader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartReading() throws Exception {
        inputReader.startReading();
    }

    @Test
    public void testStop() throws Exception {
        inputReader.stop();
    }
}