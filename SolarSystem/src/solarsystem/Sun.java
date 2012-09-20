/**
 * Sun
 * @author dima6120
 */

package solarsystem;

import java.util.Iterator;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Sun extends Group{
    GLUquadric disk;
    public Sun(float _x, float _y, float _r) {
        x = _x; y = _y; r = _r; owner = null; 
        c[0] = 1.0f; c[1] = 1.0f; c[2] = 0.0f;
        
        GLU glu = new GLU();
        
        disk = glu.gluNewQuadric();
    }
    @Override
    public void Show(GL gl, GLU glu) {
        
        gl.glPushMatrix();
        
        gl.glTranslatef(x, y, 0);
        gl.glColor3f(c[0], c[1], c[2]);
        glu.gluDisk(disk, 0.0f, r, 20, 1);
        
        gl.glPopMatrix();
        
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
}
