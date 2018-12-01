package chat;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import static org.mockito.Mockito.*;

public class UserTest {
    @Mock
    Logger logger;
    @Mock
    Socket socket;
    @Mock
    DataOutputStream outputStream;
    @Mock
    DataInputStream inputStream;
    @InjectMocks
    User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCloseConnection() throws Exception {
        user.closeConnection();
    }

    @Test
    public void testGetDataInputStream() throws Exception {
        DataInputStream result = user.getDataInputStream();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetDataOutputStream() throws Exception {
        DataOutputStream result = user.getDataOutputStream();
        Assert.assertEquals(null, result);
    }
}