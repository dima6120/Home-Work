/**
 * Main
 * @author dima6120
 */

import hashtable.*;

public class Main {
    public static void main(String[] args) {
       HashTable hs = new HashTable(10);
       hs.putElem("abcsdfghjgfdsghgfrydtgfkhfyrtdgfrs");
       hs.putElem("abd");
       hs.putElem("abcd");
       hs.putElem("abcd");
       System.out.println(Integer.toString(hs.getbyKey("abcd").getValue()));
    }  
}