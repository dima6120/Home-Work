/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Main
 * @author dima6120
 */

package main;

import calculator.Calculator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
       Calculator calc = new Calculator();
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       String s;
       do {
            s = br.readLine();
            if (s.equals("exit")) {
                break;
            }
            calc.expr(s);
       } while (true);
    }
}
