/**
 * ������� ������� ���������, (�) 2012 ��� 
 * TObject
 * @author dima6120
 */

package solarsystem;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public abstract class TObject {
    protected GLUquadric disk; //��� ��������� �����
    protected TObject owner; //�������� �������
    protected float x;
    protected float y;
    protected float r; //������
    protected float[] c = new float[3]; //���� RGB
    public abstract void Show(GL gl, GLU glu);
    public abstract void Move();
    protected abstract void GetEvent(Event e);
    protected abstract void PutEvent(Event e);
}
