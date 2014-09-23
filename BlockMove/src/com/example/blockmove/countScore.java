package com.example.blockmove;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.R;
import android.R.color;
import android.R.string;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder.Callback;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.View.OnClickListener;

public class countScore extends SurfaceView implements Runnable,SurfaceHolder.Callback{
	private Paint paint_black, paint_white;
	private RectF rectf;
	private float tb;
	private int blackColor = 0x70000000; // 搴曢粦鑹�
	private int whiteColor = 0xddffffff; // 鐧借壊
	private int score;
	private SurfaceHolder sh = null;
	private Handler handler;
	private float arc_y = 0f;
	private int score_text = 0;
	boolean isStart = false;
	private int height;
	private int width;
	boolean isExit =false;
	public countScore(Context context,Handler handler, int score)
	{
		super(context);
		this.score =score;
		this.handler = handler;
		saveScore(context, score);
		sh = getHolder();
		sh.addCallback(this);
	}
	private void saveScore(Context context,int score)
	{
		SharedPreferences sp = context.getSharedPreferences("HeightScore",Context.MODE_PRIVATE);
		int heightScore = sp.getInt("score",0);
		if(heightScore < score)
		{
			Editor edi = sp.edit();
			edi.putInt("score", score);
			edi.commit();
			Log.v("score", "HeightScore");
		}
		
	}
	private void init(int sc,Handler handler)
	{
		
		this.score = sc;
		this.handler = handler;
		tb = 20.0f;
		width = getWidth();
		height = getHeight();
		
		paint_black = new Paint();
		paint_black.setColor(blackColor);
		paint_black.setStyle(Style.STROKE);
		paint_black.setAntiAlias(true);
		paint_black.setStrokeWidth(width * 0.004f);
		
		paint_white = new Paint();
		paint_white.setAntiAlias(true);
		paint_white.setColor(whiteColor);	
		paint_white.setStyle(Style.STROKE);
		paint_white.setTextAlign(Align.CENTER);
		paint_white.setTextSize(width * 0.2f);
		paint_white.setStrokeWidth(width * 0.004f);
		
		rectf = new RectF(width * 0.1f, height/2f - width * 0.4f, width * 0.9f, height/2f + width * 0.4f);
		Thread game = new Thread(this);
		game.start();
		
	}
	
	public void Draw() 
	{
		Canvas c = sh.lockCanvas();
		try
		{
			c.drawColor(Color.GRAY);
			c.drawArc(rectf, -90, 360, false, paint_black);
			c.drawArc(rectf, -90, arc_y, false, paint_white);
			c.drawText("" + score_text, width/2f, height/2f - paint_white.getFontMetrics().top / 2f, paint_white);
		}
		catch(Exception e)
		{
			Log.v("error","重绘失败");
		}
		finally
		{
			sh.unlockCanvasAndPost(c);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		isExit = true;
		Message msg = new Message();
		msg.what = 1;
		handler.sendMessage(msg);
		return true;
	}
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		init(score,handler);
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!isExit)
		{
			if(score_text >= score)
			{
				
				arc_y = 360f;
				score_text = score;
				isStart = true;
				Draw();				
				break;
			}
			try
			{
				Thread.sleep(10);
				if(score == 0)
					arc_y = 360f;
				else
					arc_y += 360f / 100 ;
				score_text += score / 100;
				synchronized (sh) {
				Draw();
				}
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
				
		}
	}
}
