/**
 * Main
 * @author dima6120
 */

package main;

import combcoins.NumbCombs;

public class Main {

    public static void main(String[] args) {
        int []c = {1,2,5,10,20,50};
        NumbCombs nc = new NumbCombs(c);
        System.out.println(nc.getNumbs(100));
    }
}
