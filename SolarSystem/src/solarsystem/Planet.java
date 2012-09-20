/**
 * Planet
 * @author dima6120
 */

package solarsystem;

import java.util.Iterator;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class Planet extends TPlanet{
    public Planet(float _r, float[] _c, TObject _owner, float _ds, 
            double _alphas, double _dalpha) {
        r = _r; c = _c.clone(); owner = _owner; ds = owner.r + _ds; alphas = _alphas;
        dalpha = _dalpha;
        x = (float) (owner.x + ds*Math.cos(alphas*Math.PI/180));
        y = (float) (owner.y - ds*Math.sin(alphas*Math.PI/180));
        GLU glu = new GLU();
        
        disk = glu.gluNewQuadric();
    }

    @Override
    public void Show(GL gl,GLU glu) {
        x = (float) (owner.x + ds*Math.cos(alphas*Math.PI/180));
        y = (float) (owner.y - ds*Math.sin(alphas*Math.PI/180));
      
        gl.glPushMatrix();
        
        gl.glTranslatef(x, y, 0);
        gl.glColor3f(c[0], c[1], c[2]);
        glu.gluDisk(disk, 0.0f, r, 20, 1);
        
        gl.glPopMatrix();
        
        if (!list.isEmpty()) {
            Iterator i = list.iterator(); TObject o;
                
            while(i.hasNext()) {
                o = (TObject) i.next();
                o.Show(gl, glu);
            }
        }
    }

    @Override
    public void Move() {
        alphas += dalpha; 
        if (alphas >= 360.0f) {
            alphas -=360.0f; 
        }
        if (!list.isEmpty()) {
            Iterator i = list.iterator(); TObject o;
                
            while(i.hasNext()) {
                o = (TObject) i.next();
                o.Move();
            }
        }
    }
    
}
