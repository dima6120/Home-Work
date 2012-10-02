/**
 * Main
 * @author dima6120
 */

package main;

import static checkemail.CheckEmail.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;
        s = br.readLine();
        System.out.println(testEmail(s));
    }
}
