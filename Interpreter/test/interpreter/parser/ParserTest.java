
package interpreter.parser;

import interpreter.Interpreter;
import interpreter.LazyInterpreter;
import interpreter.treenodes.Expression;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * ParserTest
 * @author dima6120
 */
public class ParserTest {

    public ParserTest() {
    }

    /**
     * Test of parse method, of class Parser.
     */
    @Test
    public void testParse1() throws Exception {
        System.out.println("parse1");
        String text = "let f = fun x -> fun y -> x + y in f 1 (f 1 (f 1 1))";
        Parser instance = new Parser();
        Interpreter i = new LazyInterpreter();
        String expResult = "4";
        Expression e = i.eval((Expression)instance.parse(text));
        String result = e.toString();
        assertEquals(expResult, result);
    }
    @Test
    public void testParse2() throws Exception {
        System.out.println("parse2");
        String text = "let f = fun x -> fun y -> fun z -> fun w -> (x + (y + z)*2+1)*w in let x = (1) in (f (f 1 x 3 4) x) (x) ((fun x -> f x 1 1 (x+1)) 1)";
        Parser instance = new Parser();
        Interpreter i = new LazyInterpreter();
        String expResult = "540";
        Expression e = i.eval((Expression)instance.parse(text));
        String result = e.toString();
        assertEquals(expResult, result);
    }
    @Test
    public void testParse3() throws Exception {
        System.out.println("parse3");
        String text = "((fun x -> x) (fun x -> x + 5)) 3";
        Parser instance = new Parser();
        Interpreter i = new LazyInterpreter();
        String expResult = "8";
        Expression e = i.eval((Expression)instance.parse(text));
        String result = e.toString();
        assertEquals(expResult, result);
    }
    @Test
    public void testParse4() throws Exception {
        System.out.println("parse4");
        String text = "let f = fun x -> x + 1 in f (let y = 1 in f y)";
        Parser instance = new Parser();
        Interpreter i = new LazyInterpreter();
        String expResult = "3";
        Expression e = i.eval((Expression)instance.parse(text));
        String result = e.toString();
        assertEquals(expResult, result);
    }
     @Test
    public void testParse5() throws Exception {
        System.out.println("parse5");
        String text = "let f = fun x -> x + 1 in f (f 2) / f 1";
        Parser instance = new Parser();
        Interpreter i = new LazyInterpreter();
        String expResult = "2";
        Expression e = i.eval((Expression)instance.parse(text));
        String result = e.toString();
        assertEquals(expResult, result);
    }
    
}