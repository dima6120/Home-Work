/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * ParserTest
 * @author dima6120
 */

package interpreter.parser;

import static org.junit.Assert.*;
import org.junit.Test;

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
        String expResult = "[let f = [fun x -> [fun y -> (x + y)]] in [call [call f (1)] ([call [call f (1)] ([call [call f (1)] (1)])])]]";
        String result = instance.parse(text).toString();
        assertEquals(expResult, result);
    }
    @Test
    public void testParse2() throws Exception {
        System.out.println("parse2");
        String text = "let f = fun x -> fun y -> fun z -> fun w -> (x + (y + z)*2+1)*w in let x = (1) in (f (f 1 x 3 4) x) (x) ((fun x -> f x 1 1 (x+1)) 1)";
        Parser instance = new Parser();
        String expResult = "[let f = [fun x -> [fun y -> [fun z -> [fun w -> ((x + (((y + z) * 2) + 1)) * w)]]]] in [let x = 1 in [call [call [call [call f ([call [call [call [call f (1)] (x)] (3)] (4)])] (x)] (x)] ([call [fun x -> [call [call [call [call f (x)] (1)] (1)] ((x + 1))]] (1)])]]]";
        String result = instance.parse(text).toString();
        assertEquals(expResult, result);
    }
    @Test
    public void testParse3() throws Exception {
        System.out.println("parse3");
        String text = "((fun x -> x) (fun x -> x + 5)) 3";
        Parser instance = new Parser();
        String expResult = "[call [call [fun x -> x] ([fun x -> (x + 5)])] (3)]";
        String result = instance.parse(text).toString();
        assertEquals(expResult, result);
    }
    @Test
    public void testParse4() throws Exception {
        System.out.println("parse4");
        String text = "let f = fun x -> x + 1 in f (let y = 1 in f y)";
        Parser instance = new Parser();
        String expResult = "[let f = [fun x -> (x + 1)] in [call f ([let y = 1 in [call f (y)]])]]";
        String result = instance.parse(text).toString();
        assertEquals(expResult, result);
    }
     @Test
    public void testParse5() throws Exception {
        System.out.println("parse5");
        String text = "let f = fun x -> x + 1 in f (f 2) / f 1";
        Parser instance = new Parser();
        String expResult = "[let f = [fun x -> (x + 1)] in ([call f ([call f (2)])] / [call f (1)])]";
        String result = instance.parse(text).toString();
        assertEquals(expResult, result);
    }
    
}