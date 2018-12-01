package client;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ClientTest {
    @Mock
    MessageReceiver messageReceiver;
    @Mock
    InputReader inputReader;
    @InjectMocks
    Client client;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInit() throws Exception {
        client.init();
    }
}