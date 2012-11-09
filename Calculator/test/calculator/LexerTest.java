
package calculator;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * LexerTest
 * @author dima6120
 */
public class LexerTest {

    public LexerTest() {
    }

    /**
     * Test of curlex method, of class Lexer.
     */
    @Test
    public void testCurlex() {
        System.out.println("curlex");
        Calculator calc = new Calculator();
        Lexer instance = new Lexer(calc);
        boolean expResult = true;
        String []s = {"x","1x","xy1z1","12", "%","+","-","*","/","(",")","="}; 
        Lexem []el = {Lexem.VAR, Lexem.NUMB, Lexem.VAR, Lexem.NUMB, Lexem.ERR,
        Lexem.PLUS, Lexem.MINUS, Lexem.MULT, Lexem.DIV, Lexem.OBR, Lexem.CBR,
        Lexem.ASSIGN};
        for (int i = 0; i < 12; i++) {
            instance.setLine(s[i]);
            instance.nextlex();
            assertTrue(expResult && 
                       instance.curlex() == el[i]
                      );
            System.out.println(i);
        }
    }
}