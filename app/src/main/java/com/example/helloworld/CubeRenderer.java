package com.example.helloworld;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CubeRenderer implements GLSurfaceView.Renderer{
    private Cube mCube;
    private float mTransY;
    private float mAngle;
    public final static int SS_SUNLIGHT = GL10.GL_LIGHT0;

    public CubeRenderer(){
        mCube = new Cube();
    }
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
       // gl.glDisable(GL10.GL_DITHER);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl.glClearColor(0,0,0,0);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glShadeModel(GL10.GL_SMOOTH);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glCullFace(GL10.GL_FRONT);
        gl.glDepthMask(false);
      //  initLighting(gl);
    }
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        float fieldOfView = 10.0f/57.3f;
        float aspectRatio = (float)width/(float)height;
        float zNear = 6f;
        float zFar = 1000;
        float size = zNear * (float)(Math.tan((double)(fieldOfView/2.0f)));
        gl.glFrustumf(-size, size, -size/aspectRatio, size/aspectRatio, zNear, zFar);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
    }
    public void onDrawFrame(GL10 gl)
    {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, (float) Math.sin(mTransY), -20f);
        mTransY += 0.075f;
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glRotatef(mAngle, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(mAngle, 0.0f, 1.0f, 0.0f);
        gl.glScalef(1,2,1);
        mAngle += 0.4;
        mCube.draw(gl);
    }
    public void initLighting(GL10 gl){
        float[] green = {0.0f, 1.0f, 0.0f, 1.0f};
        float[] position = {10.0f,0.0f,3.0f,1.0f};
        float[] red = {1.0f, 0.0f, 0.0f, 1.0f};
        float[] blue = {0.0f, 0.0f, 1.0f, 1.0f};
        float[] yellow = {1.0f, 1.0f, 0.0f, 1.0f};
       //gl.glLightfv(SS_SUNLIGHT, GL10.GL_POSITION, makeFloatBuffer(position));

        //gl.glLightfv(SS_SUNLIGHT, GL10.GL_DIFFUSE, makeFloatBuffer(green));
      //gl.glLightfv(SS_SUNLIGHT,GL10.GL_SPECULAR, makeFloatBuffer(red));

        //gl.glLightfv(SS_SUNLIGHT, GL10.GL_AMBIENT, makeFloatBuffer(blue));

        float[] colorVector={0.2f,0.2f, 0.2f, 1.0f};
       // gl.glLightModelfv(GL10.GL_LIGHT_MODEL_TWO_SIDE, makeFloatBuffer(colorVector));  gl.glShadeModel(GL10.GL_SMOOTH);
       // gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(SS_SUNLIGHT);
        gl.glEnable(GL10.GL_COLOR_MATERIAL);
       //gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, makeFloatBuffer(red));
      // gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, makeFloatBuffer(red));
      // gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, makeFloatBuffer(blue));
       //gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, makeFloatBuffer(yellow));
       // gl.glMaterialf(GL10.GL_FRONT_AND_BACK,GL10.GL_SHININESS, 25);
    }
    protected static FloatBuffer makeFloatBuffer(float[] array)
    {
        ByteBuffer bb = ByteBuffer.allocateDirect(array.length*4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(array);
        fb.position(0);
        return fb;
    }

}
