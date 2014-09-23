package com.example.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;

public class traversingRect extends View{

	private Paint paint_black, paint_white;
	private RectF rectf;
	private float width;
	private float height;
	public traversingRect(Context content) {
		// TODO Auto-generated constructor stub
		super (content);
		init();
	}
	
	private void init(){
		paint_black = new Paint();
		paint_black.setColor(Color.BLACK);
		paint_black.setStyle(Style.STROKE);
		paint_black.setAntiAlias(true);
		paint_black.setStrokeWidth(2.2f);
		
		

		
		this.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				// TODO Auto-generated method stub
				
				width = getWidth();
				height = getHeight();
				rectf = new RectF(0, 0, width, height);
				return false;
			}
		});
	}
	 protected void onDraw(Canvas canvas) {
		 super.onDraw(canvas);
		 width = getWidth();
			height = getHeight();
			rectf = new RectF(0, 0, width, height);
		 canvas.drawColor(Color.WHITE);
		 canvas.drawRect(rectf, paint_black);
		 		
	}
	
}
