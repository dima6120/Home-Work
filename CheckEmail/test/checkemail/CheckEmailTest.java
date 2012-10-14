package checkemail;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * @author dima6120
 */
public class CheckEmailTest {
    
    public CheckEmailTest() {
    }

    @Test
    public void EmptyStr() {
        System.out.println("EmptyStr");
        String email = "";
        boolean expResult = false;
        boolean result = CheckEmail.testEmail(email);
        assertEquals(expResult, result);
    }
    @Test
    public void NullStr() {
        System.out.println("NullStr");
        String email = null;
        boolean expResult = false;
        boolean result = CheckEmail.testEmail(email);
        assertEquals(expResult, result);
    }
    @Test
    public void RightEmail() {
        System.out.println("RigthEmail");
        String []email = {"a@b.cc", "victor.polozov@mail.ru",
        "my@domain.info","_.1@mail.com","coins@hermitage.museum"};
        boolean expResult = true;
        boolean result = true;
        for(int i = 0; i < email.length; i++) {
            result = result && CheckEmail.testEmail(email[i]);
        }
        assertEquals(expResult, result);
    }
    @Test
    public void WrongEmail() {
        System.out.println("wrongEmail");
        String []email = {"a@b.c", "a..b@mail.ru",
        ".a@mail.ru","yo@domain.domain","1@mail.ru"};
        boolean expResult = false;
        boolean result = false;
        for(int i = 0; i < email.length; i++) {
            result = result || CheckEmail.testEmail(email[i]);
        }
        assertEquals(expResult, result);
    }
}
