package com.ml.rpg2;

public class PerSecondCounter {
	protected GameTime _timer;
	protected int _frames, _fps;
	protected float _frameTime;
	
	public PerSecondCounter() {
		_timer = new GameTime();
		_frames = _fps = 0;
		_frameTime = 0f;
	}
	
	public void update() {
		_frames++;
		_timer.update();
		_frameTime += (float) _timer.getElapsedSeconds();
		if (_frameTime >= 1f) {
			_frameTime -= 1f;
			_fps = _frames;
			_frames = 0;
		}
	}
	
	public int getFPS() {
		return _fps;
	}
	
	public String toString() {
		return String.valueOf(_fps);
	}
}
