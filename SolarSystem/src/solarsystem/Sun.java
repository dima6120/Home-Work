/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * Sun
 * @author dima6120
 */

package solarsystem;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Sun extends Group{
    public Sun(float _x, float _y, float _r, TObject _owner) {
        x = _x; y = _y; r = _r; owner = _owner; 
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
        
        super.Show(gl, glu);
    }   

    @Override
    public void Move() {
        super.Move();
    }
    
    @Override
    protected void GetEvent(Event e) {
        if ((e.addr == null || e.addr == this) && e.sender != this){         
            switch (e.type) {
                case vercol: 
                    float sx = e.sender.x, sy = e.sender.y,
                          dx = ((Comet)e.sender).dx,
                          dy = ((Comet)e.sender).dy;
                    if (Math.sqrt(Math.pow(sx-x,2) + Math.pow(sy-y,2)
                            ) <= r + 0.03) {
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
