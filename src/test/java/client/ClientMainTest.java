package client;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ClientMainTest {
    @Mock
    Logger logger;
    @InjectMocks
    ClientMain clientMain;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMain() throws Exception {
        ClientMain.main(new String[]{"args"});
    }
}