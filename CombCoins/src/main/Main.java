/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Main
 * @author dima6120
 */

package main;

import combcoins.ArgumentException;
import combcoins.NumbCombs;

public class Main {

    public static void main(String[] args) throws ArgumentException {
        int []c = {1,2,5,10,20,50};
        NumbCombs nc = new NumbCombs(c);
        System.out.println(nc.getNumbs(100));
    }
}
