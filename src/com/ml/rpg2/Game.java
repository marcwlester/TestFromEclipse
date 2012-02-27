package com.ml.rpg2;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class Game {
	protected GameThread _gameThread;
	protected Thread _thread;
	protected OpenGLRenderer _renderer;
	protected GLSurfaceView _view;
	protected Context _context;
	
	protected boolean _paused = false;
	
	
	protected ArrayList<GameComponent> _components;
	protected boolean _running = false;
	
	public Game(Context context) {
		_context = context;
		_components = new ArrayList<GameComponent>();
		_renderer = new OpenGLRenderer(_context, this);
	}
	
	public Context getContext() {
		return _context;
	}
	
	public void setView(GLSurfaceView view) {
		_view = view;
		_view.setRenderer(_renderer);
	}
	
	public GLSurfaceView getView() {
		return _view;
	}
	
	public OpenGLRenderer getRenderer() {
		return _renderer;
	}
	
	public void initialize() {
		_gameThread = new GameThread(this, _renderer);
		for (GameComponent component: _components) {
			component.initialize();
		}
		start();
	}
	
	public void start() {
		if (!_running ) {
			_thread = new Thread(_gameThread);
			_thread.setName("Game Thread");
			_thread.start();
			_running = true;
			onStart();
		} else {
			_gameThread.resumeGame();
		}
	}
	
	public void stop() {
		if (_running) {
			if (_gameThread.isPaused()) {
				_gameThread.resumeGame();
			}
			_gameThread.stopGame();
			try {
				_thread.join();
			} catch (InterruptedException e) {
				_thread.interrupt();
			}
			_thread = null;
			_running = false;
			onStop();
		}
	}
	
	public void draw(GL10 gl) {
		onDraw(gl);
		for (GameComponent component: _components) {
			if (component instanceof DrawableComponent) {
				((DrawableComponent) component).onDraw(gl);
			}
		}
	}
	
	public void update(GameTime gameTime) {
		onUpdate(gameTime);
		for (GameComponent component: _components) {
			if (component instanceof UpdatableComponent) {
				((UpdatableComponent) component).onUpdate(gameTime);
			}
		}
	}
	
	public void onUpdate(GameTime gameTime) {
		
	}
	
	public void onDraw(GL10 gl) {
		
	}

	public void onPause() {
		// TODO Auto-generated method stub
		if (_running) {
			_paused = true;
			_gameThread.pauseGame();
		}
		
	}

	public void onResume(Context context) {
		// TODO Auto-generated method stub
		if (_running) {
			_paused = false;
			_gameThread.resumeGame();
		} else {
			_renderer.setContext(context);
		}
	}
	
	public void onStart() {
		
	}
	
	public void onStop() {
		
	}

	public boolean isPaused() {
		// TODO Auto-generated method stub
		return _paused;
	}

	public void onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig glConfig) {
		// TODO Auto-generated method stub
		
	}
}
