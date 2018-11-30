package plugins;

import org.junit.Assert;
import org.junit.Test;

public class PowerTest {
    Power power = new Power(2, 5);

    @Test
    public void testCall() throws Exception {
        Double result = power.call();
        Assert.assertEquals(Double.valueOf(32), result);
    }
}