package ways;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author dima6120
 */
public class CountWaysTest {
    
    public CountWaysTest() {
    }

    /**
     * Test of getNumbWays method, of class CountWays.
     */
    @Test
    public void DefPoint() throws ArgException {
        System.out.println("DefPoint");
        int x = 2;
        int y = 3;
        CountWays instance = new CountWays();
        int expResult = 10;
        int result = instance.getNumbWays(x, y);
        assertEquals(expResult, result);
    }
    @Test(expected = ArgException.class)
    public void NegPoint() throws ArgException {
        System.out.println("ZeroZero");
        int x = -1;
        int y = 0;
        CountWays instance = new CountWays();
        instance.getNumbWays(x, y);
    }
}
