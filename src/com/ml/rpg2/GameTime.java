package com.ml.rpg2;

public class GameTime {
	
	protected long _time;
	protected long _elapsed;
	
	public GameTime() {
		_time = System.nanoTime();
	}
	
	public GameTime update() {
		long time = System.nanoTime();
		_elapsed = time - _time;
		_time = time;
		return this;
	}
	
	public long getTime() {
		return _time;
	}
	
	public long getElapsed() {
		return _elapsed;
	}
	
	public double getElapsedSeconds() {
		return (double)((double)_elapsed / 1000000000);
	}
	
	public double getElapsedMilliSeconds() {
		return (double)((double)_elapsed / 1000000);
	}
}

