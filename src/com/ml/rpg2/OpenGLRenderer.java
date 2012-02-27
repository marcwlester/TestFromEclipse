package com.ml.rpg2;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

public class OpenGLRenderer implements Renderer {

	protected GLSurfaceView _view;
	protected PerSecondCounter _fps;
	protected Game _game;
	protected Object _drawLock;
	protected Context _context;
	
	

	public OpenGLRenderer(Context context, Game game) {
		_context = context;
		_fps = new PerSecondCounter();
		_game = game;
		_drawLock = new Object();
	}
	
	public PerSecondCounter getFps() {
		return _fps;
	}
	
	public synchronized void waitUntilComplete() {
    }
	
	@Override
	public void onDrawFrame(GL10 gl) {
		synchronized (this) {
			Log.d("OpenGLRenderer", "Rendering");
			_game.draw(gl);
			_fps.update();
		}
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		synchronized (this) {
			_game.onSurfaceChanged(gl, width, height);
		}

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig glConfig) {
		synchronized (this) {
			_game.onSurfaceCreated(gl, glConfig);
		}
	}

	public void setContext(Context context) {
		_context = context;
	}
	
}
