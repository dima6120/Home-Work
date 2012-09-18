/**
 * HTElem
 * @author dima6120
 */ 

package hashtable;

public class HTElem {
    protected String str;
    protected int value;
    
    public HTElem(String s, int v) {
        str = s; value = v;
    }
    public String getStr() {
        return str;
    }
    public int getValue() {
        return value;
    } 
}
