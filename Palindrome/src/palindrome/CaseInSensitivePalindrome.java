/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * CaseInSensitivePalindrome
 * @author dima6120
 */

package palindrome;

public class CaseInSensitivePalindrome extends Palindrome {

    @Override
    boolean notequal(char c1, char c2) {
        return Character.toLowerCase(c1) != Character.toLowerCase(c2);
    }
}
