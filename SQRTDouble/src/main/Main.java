package main;

/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * Main
 * @author dima6120
 */

import static sqrt.SQRT.*;

public class Main {

    public static void main(String[] args) {
        double x = 0.01;
        System.out.println(sqrt(x));
        System.out.println(Math.sqrt(x));
        System.out.println(sqrt(x)-Math.sqrt(x));
    }
}
