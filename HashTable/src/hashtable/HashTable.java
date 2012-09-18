/**
 * HashTable
 * @author dima6120
 */ 

package hashtable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashTable {
    private int size;
    private List<List<HTElem>> table = new ArrayList<List<HTElem>>();
    
    public HashTable(int _size) {
        size = _size;
        for (int i = 0; i < size; i++) {
            table.add(new ArrayList<HTElem>());
        }
    }
    int hashfunc(String s) {
        //djb2
        int hash = 5381;
        int len = s.length();

        for(int i = 0; i < len; i++) {
            hash = ((hash << 5) + hash) + s.charAt(i);
        }
        return hash % size;
    }
    public boolean put(String key, Object elem) {
        if (key != null) {
           int hash = hashfunc(key); List<HTElem> l;
           
           if (table.get(hash).isEmpty()) {
                l = table.get(hash);
                l.add(new HTElem(key, elem));
           } else {
                l = table.get(hash);
                Iterator i = l.iterator(); HTElem e;
                
                while(i.hasNext()) {
                    e = (HTElem)i.next();
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
               Iterator i = l.iterator(); HTElem e;
                
                while(i.hasNext()) {
                    e = (HTElem)i.next();
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
           if (l.isEmpty()) {
           } else {
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
}
