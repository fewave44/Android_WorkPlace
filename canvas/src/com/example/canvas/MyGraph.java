package com.example.canvas;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;

public class MyGraph {	
	private RectF rectf, rectf_insaid;	//外面的正方形,里面长方形
	private Path path;
	private float width;		//屏幕宽
	private float height;		//屏幕高
	private float left;
	private float right;
	private float top;
	private float bottom;
	private float splite;		//间隔距离
	private float squLength;	//正方形长度
	private int dir;
	private Direction direction;
	private int pos = 0;
	private float speed_slide = 50f;
	
	public enum Direction{
		left,
		right,
		up,
		down;
	}
	public MyGraph(float width, float height) {
		// TODO Auto-generated constructor stub
		this.width = width;
		this.height = height;
		splite = (width / 3 - width / 4) / 4; 
		squLength = width/4;
		left = width/2 - squLength/2;
		right = width/2 + squLength/2;
		top = -squLength;
		bottom = 0f;
		dir = (int)(Math.random()*4);
		switch(dir){
		case 0:
			direction = Direction.left;
			drawLeft();
			break;
		case 1:
			direction = Direction.right;
			drawRight();
			break;
		case 2:
			direction = Direction.up;
			drawUp();
			break;
		case 3:
			direction = Direction.down;
			drawDown();
			break;
		}
	}
	
	public int getDir(){
		return dir;
	}
	public void setPos(int i){
		pos = i;
	}
	public void goDown(float speed){
		if(left <= splite || right >= width - splite)
			pos = 0;
		top += speed;
		bottom += speed;		
		switch(dir){
		case 0://left
			if(pos == 1)
	        {
				left -= speed_slide;
				right -= speed_slide;
				rectf_insaid.left -= speed_slide;
				rectf_insaid.right -= speed_slide;
	        }
			path = new Path();
	        path.moveTo(left + squLength/2, top);
	        path.lineTo(left + squLength/2, bottom);
	        path.lineTo(left, top + squLength/2);
	        path.close();
	        break;
		case 1://right
			if(pos == 1 && right < width - splite)
	        {
				left += speed_slide;
				right += speed_slide;
				rectf_insaid.left += speed_slide;
				rectf_insaid.right += speed_slide;
	        }
			path = new Path();
	        path.moveTo(left + squLength/2, top);
	        path.lineTo(left + squLength/2, bottom);
	        path.lineTo(right, top + squLength/2);
	        path.close();
	        break;
		case 2://up
			path = new Path();
	        path.moveTo(left, top + squLength/2);
	        path.lineTo(right, top + squLength/2);
	        path.lineTo(left + squLength/2, top);
	        path.close();
	        break;
		case 3://down
			path = new Path();
	        path.moveTo(left, bottom - squLength/2);
	        path.lineTo(right, bottom - squLength/2);
	        path.lineTo(left + squLength/2, bottom);
	        path.close();
	        break;
		}
		rectf = new RectF(left, top, right, bottom);
		rectf_insaid.top += speed;
		rectf_insaid.bottom += speed;
	}
	
	
	private void drawUp(){
		rectf = new RectF(left, top, right, bottom);
		rectf_insaid = new RectF(left + splite, top + squLength/2, right - splite, bottom - splite);
		
		path = new Path();
        path.moveTo(left, top + squLength/2);
        path.lineTo(right, top + squLength/2);
        path.lineTo(left + squLength/2, top);
        path.close();
	}
	
	private void drawDown(){
		rectf = new RectF(left, top, right, bottom);
		rectf_insaid = new RectF(left + splite, top + splite, right - splite, bottom - squLength/2);
		
		path = new Path();
        path.moveTo(left, bottom - squLength/2);
        path.lineTo(right, bottom - squLength/2);
        path.lineTo(left + squLength/2, bottom);
        path.close();
	}
	
	private void drawLeft(){
		rectf = new RectF(left, top, right, bottom);
		rectf_insaid = new RectF(left + squLength/2, top + splite, right - splite, bottom - splite);
		
		path = new Path();
        path.moveTo(left + squLength/2, top);
        path.lineTo(left + squLength/2, bottom);
        path.lineTo(left, top + squLength/2);
        path.close();
	}
	
	private void drawRight(){
		rectf = new RectF(left, top, right, bottom);
		rectf_insaid = new RectF(left + splite, top + splite, right - squLength/2, bottom - splite);
		
		path = new Path();
        path.moveTo(left + squLength/2, top);
        path.lineTo(left + squLength/2, bottom);
        path.lineTo(right, top + squLength/2);
        path.close();
	}
	
	public Path getPath(){
		return path;
	}
	public RectF getOutRectf(){
		return rectf;
	}
	public RectF getinRectf(){
		return rectf_insaid;
	}
	public float getTop(){
		return top;
	}
}
