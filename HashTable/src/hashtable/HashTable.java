/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * HashTable
 * @author dima6120
 */ 

package hashtable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static strcount.Print.*;

public class HashTable {
    private int size;
    private List<List<HTElem>> table = new ArrayList<List<HTElem>>();
    
    public HashTable(int _size) {
        size = _size;
        for (int i = 0; i < size; i++) {
            table.add(new ArrayList<HTElem>());
        }
    }
    //собираем статистику
    public void provdistr() {
        int min = -1, max = 0, av = 0, n = 0;
        double stdev = 0.0f, vdev = 0.0f;
        List<Integer> dev = new ArrayList<>(size);
        
        for(List<HTElem> l : table) {
            for(HTElem e : l) {
                n++; av++;
            }
            dev.add(n);
            if (n > max) {
                max = n;
            }
            if (min == -1) {
                min = n; 
            } else {
                if (n < min) {
                    min = n;
                }
            }
            n = 0;
        }
        for(Integer i : dev) {
            vdev += i - av/size; 
            stdev += (i - av/size)^2;
        }
        stdev = (float) Math.sqrt(stdev/size);
        print("min:" + Integer.toString(min), true);
        print("max:" + Integer.toString(max), true);
        print("average:" + Float.toString(av/size), true);
        print("average dev:" + Double.toString(vdev/size), true);
        print("standart dev:" + Double.toString(stdev), true);
    }
    /* djb2, как было сказано хеш-функция с превосходным распределением 
     * и скоростью
     * http://www.cse.yorku.ca/~oz/hash.html
    */
    private int hashfunc(String s) {
        
        long hash = 5381;
        int len = s.length();
        for(int i = 0; i < len; i++) {
            hash = ((hash << 5) + hash) + s.charAt(i);
        }
        
        return Integer.parseInt(Long.toString(Math.abs(hash % size)));
    }
    public boolean put(String key, Object elem) {
        if (key != null) {
           int hash = hashfunc(key); List<HTElem> l;
           
           if (table.get(hash).isEmpty()) {
                l = table.get(hash);
                l.add(new HTElem(key, elem));
           } else {
                l = table.get(hash);
                for(HTElem e : l) {
                    if (key.equals(e.key)) {
                        return false;
                    }
                }
                l.add(new HTElem(key, elem));
            }
            return true;
        }
        return false;
    }
    public HTElem get(String key) {
        if (key != null) {
           int hash = hashfunc(key); List<HTElem> l;
           
           l = table.get(hash);
           if (l.isEmpty()) {
               return null;
           } else {
               for(HTElem e : l) {
                    if (key.equals(e.key)) {
                        return e;
                    }
                }
           }
                   
        }
        return null;
    }
    public void delete(String key) {
        if (key != null) {
           int hash = hashfunc(key); List<HTElem> l;
           l = table.get(hash);
           if (!l.isEmpty()) {
               Iterator i = l.iterator(); HTElem e;
                
               while(i.hasNext()) {
                    e = (HTElem)i.next();
                    if (key.equals(e.key)) {
                        i.remove();
                        return;
                    }
                }
           }
        }
    }
    public void clear() {
        if (!table.isEmpty()) {
            table.clear();
        }
    }
}
