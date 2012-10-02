/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkemail;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Xiao-Mei
 */
public class CheckEmailTest {
    
    public CheckEmailTest() {
    }

    /**
     * Test of testEmail method, of class CheckEmail.
     */
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
        String []email = {"dima6120@gmail.com", "dima_6120@gmail.com",
        "dima.6120@gmail.com","d@gmail.com","dima6120@xz.gmail.com",
        "dima6120@mail.ru"};
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
        String []email = {"dima6120.@gmail.com", "dima61{0@gmail.com",
        "dima6120@.com",".@gmail.com","1@gmail.com","dima6120@gmail.c",
        "dima6120com", "dima6120@spbu..com", "dima6120@gmail.coma"};
        boolean expResult = false;
        boolean result = false;
        for(int i = 0; i < email.length; i++) {
            result = result || CheckEmail.testEmail(email[i]);
        }
        assertEquals(expResult, result);
    }
}
