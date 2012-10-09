package combcoins;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * @author dima6120
 */
public class NumbCombsTest {
    
    public NumbCombsTest() {
    }

    @Test(expected = ArgumentException.class)
    public void NullSet() throws ArgumentException {
        System.out.println("Null Set");
        int n = 0;
        NumbCombs instance = new NumbCombs(null);
        int expResult = 0;
        int result = instance.getNumbs(n);
        assertEquals(expResult, result);
    }
    @Test
    public void ZeroValue() throws ArgumentException {
        System.out.println("Zero Value");
        int []c = {1,2,5,10,20,50};
        int n = 0;
        NumbCombs instance = new NumbCombs(c);
    }
    @Test(expected = ArgumentException.class)
    public void NegValue() throws ArgumentException {
        System.out.println("Neg Value");
        int []c = {1,2,5,10,20,50};
        int n = -2;
        NumbCombs instance = new NumbCombs(c);
        instance.getNumbs(n);
    }
    @Test
    public void UsualValue() throws ArgumentException {
        System.out.println("Usual Value");
        int []c = {1,2,5,10,20,50};
        int n = 5;
        NumbCombs instance = new NumbCombs(c);
        int expResult = 4;
        int result = instance.getNumbs(n);
        assertEquals(expResult, result);
    }
}
