/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * CaseSensitivePalindrome
 * @author dima6120
 */

package palindrome;

public class CaseSensitivePalindrome extends Palindrome {    

    @Override
    boolean notequal(char c1, char c2) {
        return c1 != c2;
    }
}
