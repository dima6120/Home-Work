/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ways;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xiao-Mei
 */
public class CountWaysTest {
    
    public CountWaysTest() {
    }

    /**
     * Test of getNumbWays method, of class CountWays.
     */
    @Test
    public void DefPoint() throws Exception {
        System.out.println("DefPoint");
        int x = 2;
        int y = 3;
        CountWays instance = new CountWays();
        int expResult = 10;
        int result = instance.getNumbWays(x, y);
        assertEquals(expResult, result);
    }
    @Test(expected = ArgumentException.class)
    public void NegPoint() throws Exception {
        System.out.println("ZeroZero");
        int x = -1;
        int y = 0;
        CountWays instance = new CountWays();
        instance.getNumbWays(x, y);
    }
}
