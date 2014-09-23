package com.example.canvas;

import com.example.canvas.myView.thread;

import android.content.Context;
import android.R;
import android.R.color;
import android.R.string;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.AvoidXfermode.Mode;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.provider.CalendarContract.Colors;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;


public class mySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable{
	
	private Paint paint_black, paint_white;
	private RectF rectf;
	private float tb;
	private int blackColor = 0x70000000; // 搴曢粦鑹�
	private int whiteColor = 0xddffffff; // 鐧借壊
	private int score;
	private float arc_y = 0f;
	private int score_text = 0;
	private SurfaceHolder sh = null;
	
	public mySurfaceView(Context context, int score) {
		// TODO Auto-generated constructor stub
		super(context);
		sh = this.getHolder();
		sh.addCallback(this);
		init(score);
	}
	public void init(int score)
	{
		this.score = score;
		Resources res = getResources();
		tb = 20.0f;
		
		paint_black = new Paint();
		paint_black.setColor(blackColor);
		paint_black.setStyle(Style.STROKE);
		paint_black.setAntiAlias(true);
		paint_black.setStrokeWidth(tb * 0.2f);
		
		paint_white = new Paint();
		paint_white.setAntiAlias(true);
		paint_white.setColor(whiteColor);	
		paint_white.setStyle(Style.STROKE);
		paint_white.setTextAlign(Align.CENTER);
		paint_white.setTextSize(tb * 6.0f);
		paint_white.setStrokeWidth(tb * 0.2f);
		
		rectf = new RectF(tb * 0.5f, tb * 0.5f, tb * 18.5f, tb * 18.5f);
		setLayoutParams(new LayoutParams((int)(tb * 19.5f), (int)(tb * 19.5f)));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try{
				Thread.sleep(50);
			}
			catch(Exception e){
				Log.v("Android", "draw is Error!");
				break;
			}
			if(score_text < score)
			{
				arc_y += 3.6f;
				score_text ++;
				synchronized (sh) {
					Draw();
				}			
			}
			else
				break;
		}
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		new Thread(this).start();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void Draw() {
		Canvas c = sh.lockCanvas();
		
		c.drawColor(Color.GRAY);
		c.drawArc(rectf, -90, 360, false, paint_black);
		c.drawArc(rectf, -90, arc_y, false, paint_white);
		c.drawText("" + score_text, tb * 9.7f, tb * 11.0f, paint_white);
		sh.unlockCanvasAndPost(c);
	}
	
}
