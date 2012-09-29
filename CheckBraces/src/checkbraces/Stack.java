/**
 * Stack
 * @author dima6120
 */

package checkbraces;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    private List<StackElem> list = new ArrayList<>();
    private int head = 0;
    
    public void push(StackElem e) {
        head++;
        list.add(e);
    }
    public StackElem pop() {
        if (!isEmpty()) {
            head--;
            StackElem e = list.get(head);
            list.remove(head);
            return e;
        } else {
            return null;
        }
    }
    public boolean isEmpty() {
        return list.isEmpty();
    } 
}
