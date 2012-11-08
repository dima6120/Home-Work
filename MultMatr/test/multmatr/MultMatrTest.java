
package multmatr;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * MultMatrTest
 * @author dima6120
 */
public class MultMatrTest {

    public MultMatrTest() {
    }

    /**
     * Test of mult method, of class MultMatr.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testMultNullMatrices() {
        System.out.println("multNullMatrices");
        int[][] a = null;
        int[][] b = null;
        MultMatr instance = new MultMatr();
        instance.mult(a, b);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectMatrices1() {
        System.out.println("MultIncorrectMatrices1");
        int [][]a = {{1,2},
                     {4,5},
                     {7,8}
                    };
        int [][]b = {{1,2},
                     {4,5},
                     {7,8}
                    };
        MultMatr instance = new MultMatr();
        instance.mult(a, b);
    }
    @Test (expected = IllegalArgumentException.class)
    public void testIncorrectMatrices2() {
        System.out.println("MultIncorrectMatrices2");
        int [][]a = {{1,2,3},
                     {4,5},
                     {7,8,7}
                    };
        int [][]b = {{1,2},
                     {4,5},
                     {7,8}
                    };
        MultMatr instance = new MultMatr();
        instance.mult(a, b);
    }
    @Test
    public void testCorrectMatrices() {
        System.out.println("MultCorrectMatrices");
        int [][]a = {{1,2,3},
                     {4,5,6},
                     {7,8,9}
                    };
        int [][]b = {{1,2},
                     {4,5},
                     {7,8}
                    };
        MultMatr instance = new MultMatr();
        int [][]expResult = {{30,36},
                             {66,81},
                             {102,126}
                            };
        int [][]result = instance.mult(a, b);
        
        for (int i = 0; i < result.length; i++) {
            assertArrayEquals(expResult[i], result[i]);
        }
    }
}
