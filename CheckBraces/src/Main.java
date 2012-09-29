/**
 * Main
 * @author dima6120
 */

import checkbraces.CheckBraces;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s; CheckBraces test = new CheckBraces();
        s = br.readLine();
        System.out.println(test.testBraces(s));
    }
}
