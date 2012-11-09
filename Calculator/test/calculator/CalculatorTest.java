
package calculator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * CalculatorTest
 * @author dima6120
 */
public class CalculatorTest {

    public CalculatorTest() {
    }

    @Test
    public void testAssignR() {
        System.out.println("AssignR");
        String []s = {"x = 2", "xy12  = 2+3*(2+2)", "xyz = 2+2"};
        Calculator instance = new Calculator();
        boolean expResult = true;
        for (int i = 0; i < 3; i++) {
            instance.expr(s[i]);
            assertTrue(expResult && instance.getlastop() == OpType.ASSIGN);
            System.out.println(i);
        }
    }
    
    @Test
    public void testAssignW() {
        System.out.println("AssignW");
        String []s = {"1x = 2", "x%12  = 2+3*(2+2)", "x z = 2+2"};
        Calculator instance = new Calculator();
        boolean expResult = false;
        for (int i = 0; i < 3; i++) {
            instance.expr(s[i]);
            assertFalse(expResult || instance.getlastop() == OpType.ASSIGN);
            System.out.println(i);
        }
    }
    @Test
    public void testEvalR() {
        System.out.println("EvalR");
        String []s = {"x = 2", "x+2", "y = 2+x*(x+2)/2", 
            "z = (x + y)/2", "x + (y +z)"};
        OpType []et = {OpType.ASSIGN, OpType.EVAL, OpType.ASSIGN, 
            OpType.ASSIGN, OpType.EVAL};
        int []er = {2, 4, 6, 4, 12 };
        Calculator instance = new Calculator();
        boolean expResult = true;
        for (int i = 0; i < 4; i++) {
            instance.expr(s[i]);
            assertTrue(expResult && 
                       instance.getlastop() == et[i] &&
                       instance.getres() == er[i]
                      );
            System.out.println(i);
        }
    }
    @Test
    public void testEvalW() {
        System.out.println("EvalW");
        String []s = {"x = 2)", "x+(2", "y = 2+x(x+2)/2", 
            "z = 1 1", "x + p +z", "x = "};
        Calculator instance = new Calculator();
        boolean expResult = true;
        for (int i = 0; i < 5; i++) {
            instance.expr(s[i]);
            assertTrue(expResult && 
                       instance.getlastop() == OpType.ERROR
                      );
            System.out.println(i);
        }
    }
}