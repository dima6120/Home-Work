/**
 * CaseInSensetivePalindrome
 * @author dima6120
 */

package palindrome;

public class CaseInSensetivePalindrome extends Palindrome {
    @Override
    public boolean isPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException("передана не инициализированная "
                    + "строка!");
        }
        
        int left = 0, right = s.length() - 1;
        
        s = s.toLowerCase();
        
        while (left < right) 
        {
               while (!isletordig(s.charAt(left)))
               {
                       left++;
               }

               while (!isletordig(s.charAt(right)))
               {
                       right--;
               }

               if (s.charAt(left) != s.charAt(right))
               {
                       return false;
               }

               left++;

               right--;
        }
        
        return true;
    }
    
}
