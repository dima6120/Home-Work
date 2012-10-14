
package palindrome;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * CaseSensitivePalindromeTest
 * @author dima6120
 */
public class CaseSensitivePalindromeTest {

    public CaseSensitivePalindromeTest() {
    }

    /**
     * Test of isPalindrome method, of class CaseSensitivePalindrome.
     */
    @Test(expected = IllegalArgumentException.class)
    public void isNullS() throws IOException {
        System.out.println("isNullS");
        String s = null;
        CaseSensitivePalindrome instance = new CaseSensitivePalindrome();
        instance.isPalindrome(s);
    }
    
    @Test
    public void isnotPalindromeS() {
        System.out.println("isnotPalindromeS");
        String[] s = {"{}}{{{a'b", "Aa", "Aabaa"};
        CaseSensitivePalindrome instance = new CaseSensitivePalindrome();
        boolean expResult = false;
        boolean result = false;
        for (int i = 0; i < s.length; i++) {
            result = result || instance.isPalindrome(s[i]);
        }

        assertEquals(expResult, result);
    }

    @Test
    public void isPalindromeS() {
        System.out.println("isPalindromeS");
        String[] s = {"", "{}}{{{a'", "{}...,,", "abba", "   a.../b a",
            "AaabaaA"};
        CaseSensitivePalindrome instance = new CaseSensitivePalindrome();
        boolean expResult = true;
        boolean result = true;
        for (int i = 0; i < s.length; i++) {
            result = result && instance.isPalindrome(s[i]);
        }

        assertEquals(expResult, result);
    }

}