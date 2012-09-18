/**
 * Palindrome
 * @author dima6120
 */

package palindrome;

public abstract class Palindrome {
    boolean isletordig(char c)
    {
        if (Character.isDigit(c) || Character.isLetter(c))
        {
            return true;
        }
        return false;
    }
    public abstract boolean isPalindrome(String s);
}
