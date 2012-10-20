/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * CaseInSensitivePalindrome
 * @author dima6120
 */

package palindrome;

public class CaseInSensitivePalindrome extends Palindrome {
    @Override
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

               if (Character.toLowerCase(s.charAt(left)) 
                       != Character.toLowerCase(s.charAt(right))){
                       return false;
               }

               left++;

               right--;
        }
        
        return true;
    }
    
}
