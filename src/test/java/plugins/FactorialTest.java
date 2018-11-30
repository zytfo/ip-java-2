package plugins;

import org.junit.Assert;
import org.junit.Test;

public class FactorialTest {
    Factorial factorial = new Factorial(5);

    @Test
    public void testCall() throws Exception {
        Integer result = factorial.call();
        Assert.assertEquals(Integer.valueOf(120), result);
    }
}