package com.example.thenewboston;
import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

	public class MyBringBackSurface extends SurfaceView implements Runnable {
		SurfaceHolder ourHolder;
		Thread ourThread = null;
		Boolean isRunning = false;


		public MyBringBackSurface(Context context) {
			super(context);
			ourHolder = getHolder();

			ourThread.start();

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
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}
	}