/**
 * StrCount
 * @author dima6120
 */ 

package strcount;

import hashtable.*;

public class StrCount {
    private HashTable ht;
    
    public StrCount(int size) {
        ht = new HashTable(size);
    }
    
    public void put(String s) {
        if (s != null) {
            boolean f = ht.put(s, (int) 1);
            if (!f) {
                HTElem e = ht.get(s);
                e.setData((int)e.getData() + 1);
            }
        }
    }
    public int get(String s) {
        return (s == null) ? 0 : (int)ht.get(s).getData();
    }
}
/**
 * StrCount
 * @author dima6120
 */ 

package strcount;

import hashtable.*;

public class StrCount {
    private HashTable ht;
    
    public StrCount(int size) {
        ht = new HashTable(size);
    }
    
    public void put(String s) {
        if (s != null) {
            boolean f = ht.put(s, (int) 1);
            if (!f) {
                HTElem e = ht.get(s);
                e.setData((int)e.getData() + 1);
            }
        }
    }
    public int get(String s) {
        if (s == null) {
            return 0;
        } else {
            HTElem e = ht.get(s);
            
            if (e != null) {
                return (int)e.getData();
            }
            
            return 0;
        }
    }
    public void delete(String s) {
        ht.delete(s);
    }
}
