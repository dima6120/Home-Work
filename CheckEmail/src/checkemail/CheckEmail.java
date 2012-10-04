/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * CheckMail
 * @author dima6120
 */

package checkemail;

import java.util.regex.*;

public class CheckEmail {
    private static Pattern p = 
            Pattern.compile("^[a-zA-Z](\\.{0,}[\\w\\u005F]){0,}@([\\w]+\\.)+[a-zA-Z]{2,3}");
    public static boolean testEmail(String email) {
        if (email != null) {
            Matcher m = p.matcher(email);
            return m.matches();
        } else {
            return false; 
        }
    }
}
