package com.example.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

	MyBringBackSurface ourSurfaceView;
	float x, y, sX, sY, fX, fY, dX, dY, aX, aY, scX, scY ;
	Bitmap test, plus;

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x = 0; 
		y = 0;
		sX = 0;
		sY = 0;
		fX = 0;
		fY = 0;
		dX = dY = aX = aY = scX = scY = 0;
		test = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		plus = BitmapFactory.decodeResource(getResources(), R.drawable.pluss);
		setContentView(ourSurfaceView);
		
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
	
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = event.getX();
		y = event.getY();
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			dX = dY = aX = aY = scX = scY = fX = fY = 0;
			break;
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			dX = fX - sX;
			dY = fY - sY;
			scX = dX/30;
			scY = dY/30;
			x = 0;
			y = 0;
		
		}
		return true;
	}


	public class MyBringBackSurface extends SurfaceView implements Runnable {
		SurfaceHolder ourHolder;
		Thread ourThread = null;
		Boolean isRunning = false;


		public MyBringBackSurface(Context context) {
			super(context);
			ourHolder = getHolder();

		

		}
		public void pause()
		{
			isRunning = false;
			while(true)
			{
				try {
					ourThread.join();
					break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;
		}

		public void resume()
		{
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}
		@Override
		public void run() {
			while(isRunning)
			{
				if(!ourHolder.getSurface().isValid())
				{
					continue;
				}
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				 if(x != 0 && y != 0)
				 {
					 canvas.drawBitmap(test, x - test.getWidth()/2, y - test.getHeight()/2, null);
				 }
				 if(sX != 0 && sY != 0)
				 {
					 canvas.drawBitmap(plus, sX - plus.getWidth()/2, sY - plus.getHeight()/2, null);
				 } if(fX != 0 && fY != 0)
				 {
					 canvas.drawBitmap(test, fX - test.getWidth()/2 - aX, fY - test.getHeight()/2 - aY, null);
					 canvas.drawBitmap(plus, fX - plus.getWidth()/2, fY - plus.getHeight()/2, null);
				 }
				 
				 aX += scX;
				 aY += scY;
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
}
