package chat;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.DataOutputStream;

import static org.mockito.Mockito.*;

public class MessageTest {
    @Mock
    DataOutputStream outputStream;
    @InjectMocks
    Message message;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
