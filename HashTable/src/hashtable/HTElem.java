/**
 * HTElem
 * @author dima6120
 */ 

package hashtable;

public class HTElem {
    protected String key;
    private Object data;
    
    public HTElem(String k, Object d) {
        key = k; data = d;
    }
    public String getKey() {
        return key;
    }
    public void setData(Object d) {
        data =d;
    }
    public Object getData() {
        return data;
    } 
}
