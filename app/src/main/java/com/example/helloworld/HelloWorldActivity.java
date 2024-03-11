package com.example.helloworld;
import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
public class HelloWorldActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        GLSurfaceView view = new GLSurfaceView(this);
        view.setRenderer(new com.example.helloworld.HelloWorldRenderer());
        setContentView(view);
    }
}