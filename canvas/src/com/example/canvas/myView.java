package com.example.canvas;

import android.content.Context;
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
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;

public class myView extends View 
{
	private Paint paint_black, paint_white;
	private RectF rectf;
	private float tb;
	private int blackColor = 0x70000000; // 搴曢粦鑹�
	private int whiteColor = 0xddffffff; // 鐧借壊
	private int score;
	private float arc_y = 0f;
	private int score_text = 0;
	public myView(Context context, int score)
	{
		super(context);
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
		
		this.getViewTreeObserver().addOnPreDrawListener(
				new OnPreDrawListener() {
					
					@Override
					public boolean onPreDraw() {
						// TODO Auto-generated method stub
						new thread();
						getViewTreeObserver().removeOnPreDrawListener(this);
						return false;
					}
				});
		
	}
	
	protected void onDraw(Canvas c) 
	{
		super.onDraw(c);
		c.drawArc(rectf, -90, 360, false, paint_black);
		c.drawArc(rectf, -90, arc_y, false, paint_white);
		c.drawText("" + score_text, tb * 9.7f, tb * 11.0f, paint_white);
	}
	
	class thread extends Thread implements Runnable
	{
		private Thread thread;
		private int statek;
		int count;
		public thread()
		{
			this.thread =  new Thread(this);
			thread.start();
		}
		@Override
		public void run() {
			while(true)
			{
				switch(statek)
				{
				case 0:
					try
					{
						Thread.sleep(200);
						statek = 1;
					}
					catch(InterruptedException e)
					{
						
					}
					break;
				case 1:
					try
					{
						Thread.sleep(50);
						arc_y += 3.6f;
						score_text ++;
						count ++;
						postInvalidate();
					}
					catch(InterruptedException e)
					{
						e.printStackTrace();
					}
					break;
				}
				if(count >= score)
				{
					break;
				}
			}
		}
	}
}
