/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * TObject
 * @author dima6120
 */

package solarsystem;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public abstract class TObject {
    protected GLUquadric disk; //для рисования круга
    protected TObject owner; //владелец объекта
    protected float x;
    protected float y;
    protected float r; //радиус
    protected float[] c = new float[3]; //цвет RGB
    public abstract void Show(GL gl, GLU glu);
    public abstract void Move();
    protected abstract void GetEvent(Event e);
    protected abstract void PutEvent(Event e);
}
