package com.ml.rpg2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class Rpg2Activity extends Activity {
	protected Game _game;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _game = new TestGame(this);
        _game.setView(new Rpg2View(this));
        _game.initialize();
        setContentView(_game.getView());
        Log.d("Rpg2Activity", "onCreate");
    }
    
    public boolean onTouchEvent(MotionEvent event) {
    	Log.d("Rpg2Activity", "onTouchEvent");
    	if (!_game.isPaused()) {
    		_game.onTouchEvent(event);
    	}
    	return true;
    }
    
    protected void onPause() {
    	super.onPause();
    	_game.onPause();
    }
    
    protected void onResume() {
    	super.onResume();
    	_game.onResume(this);
    }
    
}