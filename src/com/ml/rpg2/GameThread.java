package com.ml.rpg2;

public class GameThread implements Runnable {

	protected boolean _finished = false;
	protected boolean _paused = false;
	protected Game _game;
	protected OpenGLRenderer _renderer;
	protected GameTime _gameTime;
	protected Object _pauseLock;
	
	public GameThread(Game game, OpenGLRenderer renderer) {
		_finished = false;
		_paused = false;
		_game = game;
		_renderer = renderer;
		_gameTime = new GameTime();
		_pauseLock = new Object();
	}
	
	@Override
	public void run() {
		_finished = false;
		while (!_finished) {
			_renderer.waitUntilComplete();
			
			_gameTime.update();
			double elapsed = _gameTime.getElapsedMilliSeconds();
			if (elapsed >= 16) {
				_game.update(_gameTime);
			} else {
				try {
					Thread.sleep(Math.round(16.0D - elapsed));
				} catch (InterruptedException e) {
					//nothing
				}
			}
		}
	}
	
	public void stopGame() {
		synchronized (_pauseLock) {
			_paused = false;
			_finished = true;
			_pauseLock.notifyAll();
		}
	}
	
	public void pauseGame() {
		synchronized (_pauseLock) {
			_paused = true;
		}
	}
	
	public void resumeGame() {
		synchronized (_pauseLock) {
			_paused = false;
			_pauseLock.notifyAll();
		}
	}
	
	public boolean isPaused() {
		return _paused;
	}

}
