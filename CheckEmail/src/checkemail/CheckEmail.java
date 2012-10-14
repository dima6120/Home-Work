/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * CheckMail
 * @author dima6120
 */

package checkemail;

import java.util.regex.*;

public class CheckEmail {
    private static final String name = "[a-zA-Z!#$%&'*+/=?^_`{|}~-]+(\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*";
    private static final String domain = "([a-z0-9]([a-z0-9-]*[a-z0-9])?\\.)+([A-Za-z]{2,3}|info|mobi|name|aero|jobs|museum)";
    private static Pattern p = 
            Pattern.compile(name + "@" + domain);
    public static boolean testEmail(String email) {
        if (email != null) {
            Matcher m = p.matcher(email);
            return m.matches();
        } else {
            return false; 
        }
    }
}
