/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
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
    abstract boolean notequal(char c1, char c2);
    public boolean isPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException("передана не инициализированная "
                    + "строка!");
        }
        
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
               while (!isletordig(s.charAt(left))){
                       if (left == right) {
                           return true;
                       }
                       left++;
               }

               while (!isletordig(s.charAt(right))){
                       if (left == right) {
                           return true;
                       }
                       right--;
               }

               if (notequal(s.charAt(left), s.charAt(right))) {
                   return false;
               }

               left++;

               right--;
        }
        
        return true;
    }
}
