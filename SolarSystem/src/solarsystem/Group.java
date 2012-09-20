/**
 * Group
 * @author dima6120
 */

package solarsystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Group extends TObject {
    List<TObject> list = new ArrayList<TObject>();
    public void Add(TObject o) {
        list.add(o);
    }
    public void Delete(TObject o) {
        list.remove(o);
    }
}
