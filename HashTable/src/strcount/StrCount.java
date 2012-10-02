/**
 * StrCount
 * @author dima6120
 */ 

package strcount;

import hashtable.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StrCount {
    private HashTable ht;
    private int words = 0;
    
    public StrCount(int size) {
        ht = new HashTable(size);
    }
    public void clear(int size) {
        ht.clear();
    }
    public int getNumber() {
        return words;
    }
    public void put(String s) {
        if (s != null) {
            s = s.toLowerCase();
            boolean f = ht.put(s, (int) 1);
            if (!f) {
                HTElem e = ht.get(s);
                e.setData((int)e.getData() + 1);
            }
            ++words;
        }
    }
    public int get(String s) {
        if (s == null) {
            return 0;
        } else {
            s = s.toLowerCase();
            HTElem e = ht.get(s);
            
            if (e != null) {
                return (int)e.getData();
            }
            
            return 0;
        }
    }
    public void ReadFromFile(String s) {
        try {
            try (BufferedReader f = new BufferedReader(new FileReader(s))) {
                int c = f.read(); String key = "";
                while(c != -1) {
                    while(Character.isLetter(c) || Character.isDigit(c)) {
                        key = key.concat(Character.toString((char)c).toLowerCase());
                        c = f.read();
                    } 
                    c = f.read();
                    if (!"".equals(key)) {
                        put(key); key = "";
                    }
                }
                f.close();
            }
        }
        
        catch(FileNotFoundException e)
        {
             System.out.println("File" + s + "was not found");
             System.out.println("or could not be opened.");
        }
 
        catch(IOException e)
        {
             System.out.println("Error reading from " + s + ".");
        }
    }
    public void delete(String s) {
        ht.delete(s);
    }
    public void showdistr() {
        ht.provdistr();
    }
}
