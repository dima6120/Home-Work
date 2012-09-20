/**
 * TObject
 * @author dima6120
 */

package solarsystem;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public abstract class TObject {
    protected TObject owner;
    protected float x;
    protected float y;
    protected float r;
    protected float[] c = new float[3];
    public abstract void Show(GL gl, GLU glu);
    public abstract void Move();
}
