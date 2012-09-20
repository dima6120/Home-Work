/**
 * Group
 * @author dima6120
 */

package solarsystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Group extends TObject {
    private List<TObject> list = new ArrayList<TObject>();
    public void Add(TObject o) {
        list.add(o);
    }
    public void Delete(TObject o) {
        list.remove(o);
    }

    @Override
    public void Show(GL gl, GLU glu) {
        if (!list.isEmpty()) {
            Iterator i = list.iterator(); TObject o;
                
            while(i.hasNext()) {
                o = (TObject) i.next();
                o.Show(gl,glu);
            }
        }
    }

    @Override
    public void Move() {
        if (!list.isEmpty()) {
            Iterator i = list.iterator(); TObject o;
                
            while(i.hasNext()) {
                o = (TObject) i.next();
                o.Move();
            }
        }
    }

    @Override
    protected void GetEvent(Event e) {
        if (!list.isEmpty()) {
            Iterator i = list.iterator(); TObject o;
                
            while(i.hasNext()) {
                o = (TObject) i.next();
                o.GetEvent(e);
            }
        }
    }

    @Override
    protected void PutEvent(Event e) {
        if (owner != null) {
            owner.PutEvent(e);
        } else {
            GetEvent(e);
        }
    }
}
