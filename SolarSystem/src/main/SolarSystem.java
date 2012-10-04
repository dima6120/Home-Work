/**
 * Копытов Дмитрий Сергеевич, (с) 2012 год 
 * SolarSystem
 * @author dima6120
 */

package main;

import com.sun.opengl.util.Animator;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.Timer;
import solarsystem.*;
        
public class SolarSystem implements GLEventListener {
    private Universe universe;
    private Sun sun;
    private Planet pl1;
    private Planet pl2;
    private Planet pl3;
    private Planet sp1;
    private Planet sp2;
    private Planet sp3;
    private Planet sp4;
    private int w;
    private int h;
    private GLU glu = new GLU();
    private Timer timer=new Timer(10,new ActionListener(
    ) {
        public void actionPerformed(ActionEvent ae) {
            universe.Move();
        }
    });
    
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
        GL gl = drawable.getGL();
        
        float[] c = {1.0f,0.0f,0.0f};
        
        universe = new Universe(20, 20);
        
        for(int i = 0; i < 20; i++) {
            universe.Add(new Comet(universe));
        }
        
        sun = new Sun(0.0f, 0.0f, 1.0f, universe);
        
        pl1 = new Planet(0.2f, c, sun, 2.0f, 0.0f, 0.1f);
        c[0] = 0.0f; c[1] = 1.0f; c[2] = 0.5f;
        pl2 = new Planet(0.3f, c, sun, 4.0f, 182.0f, 0.2f);
        c[0] = 1.0f; c[1] = 0.0f; c[2] = 1.0f;
        c[0] = 1.0f; c[1] = 0.0f; c[2] = 0.1f;
        pl3 = new Planet(0.4f, c, sun, 6.0f, 100.0f, 0.2f);
        
        sp1 = new Planet(0.06f, c, pl1, 0.3f, 0.0f, 0.4f);
        c[0] = 0.4f; c[1] = 0.3f; c[2] = 0.1f;
        sp2 = new Planet(0.03f, c, pl1, 0.5f, 135.0f, 0.2f);
        c[0] = 0.3f; c[1] = 0.1f; c[2] = 0.2f;
        sp3 = new Planet(0.07f, c, pl2, 1.0f, 135.0f, 0.3f);
        c[0] = 0.0f; c[1] = 0.1f; c[2] = 0.2f;
        sp4 = new Planet(0.1f, c, pl3, 1.0f, 135.0f, 0.4f);
        
        universe.Add(sun);
        sun.Add(pl2);
        sun.Add(pl1);
        sun.Add(pl3);
        pl1.Add(sp1);
        pl1.Add(sp2);
        pl2.Add(sp3);
        pl3.Add(sp4);
        timer.start();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH); 
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        
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
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        gl.glScalef(640.0f/w, 480.0f/h, 1.0f);
        gl.glPushMatrix();
        
        gl.glScalef(0.5f, 0.5f, 1.0f);
  
        universe.Show(gl, glu);
        
        gl.glPopMatrix();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

    }
}

