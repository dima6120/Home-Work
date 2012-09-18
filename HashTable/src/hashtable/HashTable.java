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
    public void putElem(String s) {
        if (s != null) {
           int hash = hashfunc(s); List<HTElem> l;
           
           if (table.get(hash).isEmpty()) {
                l = table.get(hash);
                l.add(new HTElem(s, 1));
           } else {
                l = table.get(hash);
                Iterator i = l.iterator(); HTElem e; boolean f = false;
                
                while(i.hasNext()) {
                    e = (HTElem)i.next();
                    if (s.equals(e.str)) {
                        ++e.value;
                        f = true;
                        break;
                    }
                }
                if (!f) {
                    l.add(new HTElem(s, 1));
                }
            }
        }
    }
    public HTElem getbyKey(String s) {
        if (s != null) {
           int hash = hashfunc(s); List<HTElem> l;
           
           l = table.get(hash);
           if (l.isEmpty()) {
               return null;
           } else {
               Iterator i = l.iterator(); HTElem e;
                
                while(i.hasNext()) {
                    e = (HTElem)i.next();
                    if (s.equals(e.str)) {
                        return e;
                    }
                }
           }
                   
        }
        return null;
    }
    public void deletebyKey(String s) {
        if (s != null) {
           int hash = hashfunc(s); List<HTElem> l;
           l = table.get(hash);
           if (l.isEmpty()) {
           } else {
               Iterator i = l.iterator(); HTElem e;
                
               while(i.hasNext()) {
                    e = (HTElem)i.next();
                    if (s.equals(e.str)) {
                        i.remove();
                        return;
                    }
                }
           }
        }
    }
}
