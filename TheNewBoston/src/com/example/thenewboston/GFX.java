package com.example.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.WindowManager;

public class GFX extends Activity {

	MyBringBack ourView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//wake-lock
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		super.onCreate(savedInstanceState);
		ourView = new MyBringBack(this);
		setContentView(ourView);
	}

}
