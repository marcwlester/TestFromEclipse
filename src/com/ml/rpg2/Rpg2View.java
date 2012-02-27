package com.ml.rpg2;

import android.view.SurfaceHolder;
import android.content.Context;
import android.opengl.GLSurfaceView;

public class Rpg2View extends GLSurfaceView implements SurfaceHolder.Callback {
	protected SurfaceHolder _holder;
	
	protected TestGame _game;
	
	public Rpg2View(Context context) {
		super(context);
		init();
	}
	
	public void init() {
		_holder = getHolder();
		_holder.addCallback(this);
	}
	
	public SurfaceHolder getSurfaceHolder() {
		return _holder;
	}
}
