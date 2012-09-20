package main;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;



/**
 * SolarSystem.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
import solarsystem.*;
        
public class SolarSystem implements GLEventListener {
    Sun sun;
    Planet pl1;
    Planet sp1;
    Planet sp2;
    Planet pl2;
    Planet sp3;
    Planet pl3;
    int w;
    int h;
    public static void main(String[] args) {
        Frame frame = new Frame("SolarSystem");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new SolarSystem());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        GLU glu = new GLU();
        GL gl = drawable.getGL();
        float[] c = {1.0f,0.0f,0.0f};
        sun = new Sun(0.0f, 0.0f, 1.0f);
        pl1 = new Planet(0.2f, c, sun, 2.0f, 0.0f, 0.1f);
        c[0] = 1.0f; c[1] = 0.0f; c[2] = 1.0f;
        sp1 = new Planet(0.06f, c, pl1, 0.3f, 0.0f, 0.4f);
        c[0] = 0.4f; c[1] = 0.3f; c[2] = 0.1f;
        sp2 = new Planet(0.03f, c, pl1, 0.5f, 135.0f, 0.2f);
        c[0] = 0.0f; c[1] = 1.0f; c[2] = 0.5f;
        pl2 = new Planet(0.3f, c, sun, 4.0f, 182.0f, 0.2f);
        c[0] = 0.3f; c[1] = 0.1f; c[2] = 0.2f;
        sp3 = new Planet(0.07f, c, pl2, 1.0f, 135.0f, 0.3f);
        c[0] = 1.0f; c[1] = 0.0f; c[2] = 0.1f;
        pl3 = new Planet(0.4f, c, sun, 6.0f, 100.0f, 0.2f);
        sun.Add(pl2);
        sun.Add(pl1);
        sun.Add(pl3);
        pl1.Add(sp1);
        pl1.Add(sp2);
        pl2.Add(sp3);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); 
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!
        
            height = 1;
        }
        final float _h = (float) width / (float) height;
        w = width; h = height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, _h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        gl.glScalef(640.0f/w, 480.0f/h, 1.0f);
        gl.glPushMatrix();
        
        gl.glScalef(0.5f, 0.5f, 1.0f);
        sun.Move();
        sun.Show(gl, glu);
        
        gl.glPopMatrix();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }
}

