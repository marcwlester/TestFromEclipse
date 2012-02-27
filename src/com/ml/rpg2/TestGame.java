package com.ml.rpg2;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLU;
import android.util.Log;
import android.view.MotionEvent;

public class TestGame extends Game {
	protected float[] _clearColor = {0.0f, 0.0f, 0.0f};
	
	public TestGame(Context context) {
		super(context);
	}

	@Override
	public void onTouchEvent(MotionEvent event) {
		((Activity)getContext()).setTitle(_renderer.getFps().toString());
		changeClearColor(event.getPointerCount());
	}
	
	public void onDraw(GL10 gl) {
		Log.d("TestGame", "onDraw");
		gl.glClearColor(_clearColor[0], _clearColor[1], _clearColor[2], 1.0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
	}
	
	public void onUpdate(GameTime gameTime) {
		Log.d("TestGame", "onUpdate");
	}
	
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig glConfig) {
		// TODO Auto-generated method stub
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}
	
	public void changeClearColor(int col) {
		if (col == 1) _clearColor[0] = 1.0f;
		if (col == 2) _clearColor[1] = 1.0f;
		if (col == 3) _clearColor[2] = 1.0f;
	}

}
