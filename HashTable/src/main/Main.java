/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Main
 * @author dima6120
 */

package main;

import strcount.*;
import static strcount.Print.*;

public class Main {
    public static void main(String[] args) {
        StrCount c = new StrCount(800);
        c.ReadFromFile("The Hobbit.txt");
        /*В в Ворде показывает, что всего слов - 95361, у мя - 96801, 
         это из-за того что слова типа "dining-rooms" считает за два*/
        print("words:" + c.getNumber(),true);
        /*В в Ворде показывает, что всего "it" - 1235 */
        print("i:" + c.get("it"),true);
        c.showdistr();
    }
}