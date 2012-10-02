/**
 * Main
 * @author dima6120
 */

package main;

import ways.ArgumentException;
import ways.CountWays;

public class Main {
    public static void main(String[] args) throws ArgumentException {
        CountWays cw = new CountWays();
        System.out.println(cw.getNumbWays(1, 1));
    }
}
