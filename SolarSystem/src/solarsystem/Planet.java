/**
 * Planet
 * @author dima6120
 */

package solarsystem;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

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
        
        super.Show(gl, glu);
    }

    @Override
    public void Move() {
        alphas += dalpha; 
        if (alphas >= 360.0f) {
            alphas -=360.0f; 
        }
        super.Move();
    }
    
    @Override
    protected void GetEvent(Event e) {
        if ((e.addr == null || e.addr == this) && e.sender != this) {         
            switch (e.type) {
                case vercol: 
                    float sx = e.sender.x, sy = e.sender.y,
                          dx = ((Comet)e.sender).dx,
                          dy = ((Comet)e.sender).dy;
                    if (Math.sqrt(Math.pow(sx-x,2) + Math.pow(sy-y,2)
                             ) <= r + 0.02) {
                        PutEvent(new Event(this, e.sender, TEvent.confcol));
                    } else {   
                        if (Math.sqrt(Math.pow(sx-x+dx,2) + Math.pow(sy-y+dy,2)
                             ) <= r + 0.02)
                        {
                            PutEvent(new Event(this, e.sender, TEvent.confcol));
                        }
                        super.GetEvent(e);
                    }
                    break;
            }
        } else {
            super.GetEvent(e);
        }
    }
}
