/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Main
 * @author dima6120
 */

package main;

import ways.ArgException;
import ways.CountWays;

public class Main {
    public static void main(String[] args) throws ArgException {
        CountWays cw = new CountWays();
        System.out.println(cw.getNumbWays(-1, 1));
    }
}
