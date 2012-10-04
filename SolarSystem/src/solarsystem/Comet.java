/**
 *  опытов ƒмитрий —ергеевич, (с) 2012 год 
 * Comet
 * @author dima6120
 */

package solarsystem;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Comet extends TObject{
    protected float dx = 0.01f + (float)Math.random()/15; // вектор скорости
    protected float dy = 0.01f + (float)Math.random()/15; //
    public Comet(Universe _owner) {
        owner = _owner; r = 0.04f; 
        
        int i = ((int)Math.round(Math.random()*10))%2,
            j = ((int)Math.round(Math.random()*10))%2;
        switch (i) {
            case 0: x = _owner.bx - 1 - (float)Math.random();
            case 1: x = -_owner.bx + 1 + (float)Math.random();
        }
        switch (j) {
            case 0: y = _owner.by - 1 - (float)Math.random();
            case 1: y = -_owner.by + 1 + (float)Math.random();
        }
        
        GLU glu = new GLU();
        
        disk = glu.gluNewQuadric();
    }
    @Override
    public void Show(GL gl, GLU glu) {
        gl.glPushMatrix();
        
        gl.glTranslatef(x, y, 0);
        gl.glColor3f(1, 1, 1);
        glu.gluDisk(disk, 0.0f, r, 3, 1);
        
        gl.glPopMatrix();
    }

    @Override
    public void Move() {
        float bx = ((Universe)owner).bx, by = ((Universe)owner).by;
         
        PutEvent(new Event(this, null, TEvent.vercol));
        
        if (x+dx >= bx || x+dx <= -bx) {
            dx = -dx; x += dx;
        } else {
            x +=dx;
        }
        
        if (y+dy >= by || y+dy <= -by) {
            dy = -dy; y += dy;
        } else {
            y += dy;
        }
    }

    @Override
    protected void GetEvent(Event e) {
        if ((e.addr == null || e.addr == this) && e.sender != this) {         
            switch (e.type) {
                case confcol: 
                    x -= dx; y -= dy;
                    float a = e.sender.y - y - dy;
                    float b = x - e.sender.x + dx;
                    //измен€ем направление кометы
                    dx = (float) (((Math.pow(a,2) - Math.pow(b,2)) * dx + 
                                     2*a*b*dy)/(Math.pow(a,2) + Math.pow(b,2)));

                    dy = (float) ((-((Math.pow(a,2) - Math.pow(b,2)) * dy) + 
                                     2*a*b*dx)/(Math.pow(a,2) + Math.pow(b,2)));
                    break;
                /* дл€ проверки столкновени€ с кометами, но тормоза сильные,
                   поэтому выпилил
                case vercol: 
                    float sx = e.sender.x, 
                          sy = e.sender.y;
                    if (Math.sqrt(Math.pow(sx-x,2) + Math.pow(sy-y,2)
                            ) <= r + 0.02) {
                        PutEvent(new Event(this, e.sender, TEvent.confcol));
                    }*/
            }
        }
    }

    @Override
    protected void PutEvent(Event e) {
        if (owner != null) {
            owner.PutEvent(e);
        } 
    }
    
}
