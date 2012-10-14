package palindrome;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год CaseInSensitivePalindromeTest
 *
 * @author dima6120
 */
public class CaseInSensitivePalindromeTest {

    public CaseInSensitivePalindromeTest() {
    }

    /**
     * Test of isPalindrome method, of class CaseInSensitivePalindrome.
     */
    @Test(expected = IllegalArgumentException.class)
    public void isNullI() throws IOException {
        System.out.println("isNullI");
        String s = null;
        CaseInSensitivePalindrome instance = new CaseInSensitivePalindrome();
        instance.isPalindrome(s);
    }
    
    @Test
    public void isnotPalindromeI() {
        System.out.println("isnotPalindromeI");
        String[] s = {"{}}{{{a'b", "abAab"};
        CaseInSensitivePalindrome instance = new CaseInSensitivePalindrome();
        boolean expResult = false;
        boolean result = false;
        for (int i = 0; i < s.length; i++) {
            result = result || instance.isPalindrome(s[i]);
        }

        assertEquals(expResult, result);
    }

    @Test
    public void isPalindromeI() {
        System.out.println("isPalindromeI");
        String[] s = {"", "{}}{{{a'", "{}...,,", "abba", "   a.../b a",
            "AaabAaa"};
        CaseInSensitivePalindrome instance = new CaseInSensitivePalindrome();
        boolean expResult = true;
        boolean result = true;
        for (int i = 0; i < s.length; i++) {
            result = result && instance.isPalindrome(s[i]);
        }

        assertEquals(expResult, result);
    }
}