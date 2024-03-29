package com.example.canvas;

import java.util.ArrayList;

import com.example.canvas.myView.thread;

import android.R.string;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class whaa extends SurfaceView implements Runnable,SurfaceHolder.Callback{
	
	private Paint paint_black_STROKE, paint_balct_fill;
	private Paint paint_focus;
	private float width;
	private float height;
	private int locationX, X;
	private int locationY, Y;
	private float speed = 5;
	private SurfaceHolder sh = null;
	private int focus_gra = 0;
	
	public Thread game;
	public boolean pauseGame =false;
	private ArrayList<MyGraph> graLists = new ArrayList<MyGraph>();;
	
	public whaa(Context context) {
		// TODO Auto-generated constructor stub
		super(context);
		init();
		
		
	}
	
	private void init(){
		
		sh = getHolder();
		sh.addCallback(this); 
		
		paint_black_STROKE = new Paint();
		paint_black_STROKE.setColor(Color.BLACK);
		paint_black_STROKE.setStyle(Style.STROKE);
		paint_black_STROKE.setAntiAlias(true);
		paint_black_STROKE.setStrokeWidth(10.0f);
		
		paint_balct_fill = new Paint();
		paint_balct_fill.setColor(Color.BLACK);
		paint_balct_fill.setStyle(Style.FILL);
		paint_balct_fill.setAntiAlias(true);
		
		paint_focus = new Paint();
		paint_focus.setColor(Color.RED);
		paint_focus.setStyle(Style.STROKE);
		paint_focus.setStrokeWidth(10.0f);
		paint_focus.setAntiAlias(true);
		
	}
	
	
	private void draw(){
		Canvas canvas = sh.lockCanvas();
		try{		
		canvas.drawColor(Color.WHITE);
		for(int i = 0; i < graLists.size(); i++)
		{			
			if(i == focus_gra)
				canvas.drawRect(graLists.get(i).getOutRectf(), paint_focus);
			else
				canvas.drawRect(graLists.get(i).getOutRectf(), paint_black_STROKE);
			canvas.drawRect(graLists.get(i).getinRectf(), paint_balct_fill);
			canvas.drawPath(graLists.get(i).getPath(), paint_balct_fill);			
		}
		}
		catch(Exception e)
		{
			Log.v("android", "draw is error !");
		}
		finally
		{
			sh.unlockCanvasAndPost(canvas);
		}
	}
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		width = getWidth();
		height = getHeight();	
		graLists.add(new MyGraph(width, height));
		game = new Thread(this);
		game.start();
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!pauseGame)
		{
			try{
				Thread.sleep(0);
			}
			catch(Exception e){
				System.out.print("error");
				break;
			}
			synchronized (sh) {
				for(int i = 0; i < graLists.size(); i ++)
				{
					graLists.get(i).goDown(10f);
					if(graLists.get(i).getTop() == height)
					{
						graLists.remove(i);
						if(focus_gra > 0)
							focus_gra --;
					}
				}
				if(graLists.get(graLists.size() - 1).getTop() > 10 )
					graLists.add(new MyGraph(width, height));
				draw();
			}
		}
	}
	

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			X = locationX = (int)event.getX();
			Y = locationY = (int)event.getY();			
			return true;
		case MotionEvent.ACTION_MOVE:
			X = (int)event.getX();
			Y = (int)event.getY();
			return true;
		case MotionEvent.ACTION_UP:
			Judge(X, Y);
			return true;
		}
		return super.onTouchEvent(event);		
		
	}
	private void Judge(int x, int y){
		switch(graLists.get(focus_gra).getDir())
		{
		case 0:
			if(X < locationX && Math.abs(locationX - X) > Math.abs(locationY - Y))
			{
				graLists.get(focus_gra).setPos(1);
				focus_gra ++;
			}
			break;
		case 1:
			if(X > locationX && Math.abs(X - locationX) > Math.abs(locationY - Y))
			{
				graLists.get(focus_gra).setPos(1);
				focus_gra ++;
			}
			break;
		case 2:
			if(Y < locationY && Math.abs(locationX - X) < Math.abs(locationY - Y))
			{
				graLists.remove(focus_gra);
			}
			break;
		case 3:
			if(Y > locationY && Math.abs(locationX - X) < Math.abs(locationY - Y))
			{
				graLists.remove(focus_gra);
			}
			break;
		}
		if(graLists.size()-1 < focus_gra)
			graLists.add(new MyGraph(width, height));
		draw();
	}

}
