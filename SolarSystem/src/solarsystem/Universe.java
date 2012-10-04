/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год
 * Universe
 * @author dima6120
 */

package solarsystem;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Universe extends Group{
    {
        owner = null;
    }
    protected float bx;
    protected float by;
    
    public Universe(float _bx, float _by) {
        bx = _bx; by = _by;
    }
    @Override
    public void Show(GL gl, GLU glu) {
        super.Show(gl, glu);
    }

    @Override
    public void Move() {
        super.Move();
    }
    
}
