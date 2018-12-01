package plugins;

import org.junit.Assert;
import org.junit.Test;

public class RepeaterTest {
    Repeater repeater = new Repeater(2, "text");

    @Test
    public void testCall() throws Exception {
        String result = repeater.call();
        Assert.assertEquals("text\ntext\n", result);
    }
}