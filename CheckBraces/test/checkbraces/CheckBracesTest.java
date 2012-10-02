/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkbraces;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Xiao-Mei
 */
public class CheckBracesTest {
    
    public CheckBracesTest() {
    }

    /**
     * Test of testBraces method, of class CheckBraces.
     */
    @Test
    public void EmptyStr() {
        System.out.println("EmptyStr");
        String seq = "";
        CheckBraces instance = new CheckBraces();
        boolean expResult = true;
        boolean result = instance.testBraces(seq);
        assertEquals(expResult, result);
    }
    @Test
    public void NullStr() {
        System.out.println("NullStr");
        String seq = null;
        CheckBraces instance = new CheckBraces();
        boolean expResult = false;
        boolean result = instance.testBraces(seq);
        assertEquals(expResult, result);
    }
    @Test
    public void RightSeq() {
        System.out.println("RightSeq");
        String []seq = {"<name fggs435twref][]> [()] </name> <xz /> (<xy/>)",
        "(<name> 123[([<xa />]())]fergeg </name>)[]", "(<f>dfd <name>dfd [wefw] "
                + "<x/> </name> </f>)"};
        CheckBraces instance = new CheckBraces();
        boolean expResult = true;
        boolean result = true;
        for(int i = 0; i < seq.length; i++) {
            result = result && instance.testBraces(seq[i]);
        }
        assertEquals(expResult, result);
    }
    @Test
    public void WrongSeq() {
        System.out.println("WrongSeq");
        String []seq = {"<n> (</n>)", "<> (</>)", "<n> </name>","</nf><n>",
        "<n>", "<sdfsdf   ()", ")("};
        CheckBraces instance = new CheckBraces();
        boolean expResult = false;
        boolean result = false;
        for(int i = 0; i < seq.length; i++) {
            result = result || instance.testBraces(seq[i]);
        }
        assertEquals(expResult, result);
    }
}
