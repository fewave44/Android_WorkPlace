package com.example.blockmove;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;

public class MyGraph {	
	private RectF rectf, rectf_insaid;	//�����������,���泤����
	private Path path;
	private float width;		//��Ļ��
	private float height;		//��Ļ��
	private float left;
	private float right;
	private float top;
	private float bottom;
	public static float splite;		//�������
	public static float squLength;	//�����γ���
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
		do{
		dir = (int)(Math.random()*4);
		}
		while(dir == 2);
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
			silde_down(pos);
			path = new Path();
	        path.moveTo(rectf_insaid.left, top);
	        path.lineTo(rectf_insaid.left, bottom);
	        path.lineTo(left, top + squLength/2);
	        path.close();
	        break;
		case 1://right
			silde_down(pos);
			path = new Path();
	        path.moveTo(rectf_insaid.right, top);
	        path.lineTo(rectf_insaid.right, bottom);
	        path.lineTo(right, top + squLength/2);
	        path.close();
	        break;
		case 2://up
			silde_down(pos);
			path = new Path();
	        path.moveTo(left, top + squLength/2);
	        path.lineTo(right, top + squLength/2);
	        path.lineTo(left + squLength/2, top);
	        path.close();
	        break;
		case 3://down
			silde_down(pos);
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
	
	private void silde_down(int pos){
		switch(pos){
		case 0:
			break;
		case 1:
			left -= speed_slide;
			right -= speed_slide;
			rectf_insaid.left -= speed_slide;
			rectf_insaid.right -= speed_slide;
			if(left <= splite)
			{
				pos = 0;
				rectf_insaid.left += splite - left;
				rectf_insaid.right += splite - left;
				left = splite;	
				right = splite + squLength;
			}
			break;
		case 2:
			left += speed_slide;
			right += speed_slide;
			rectf_insaid.left += speed_slide;
			rectf_insaid.right += speed_slide;
			if(right >= width - splite)
			{
				pos = 0;
				rectf_insaid.right -= right - (width - splite);
				rectf_insaid.left -= right - (width - splite);
				right = width - splite;	
				left = width - splite - squLength;
			}
			break;
		}
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
	public float getBottom(){
		return bottom;
	}
	public float getLeft(){
		return left;
	}
	public float getRight(){
		return right;
	}
}