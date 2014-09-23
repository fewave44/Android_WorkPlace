package com.example.canvas;

import android.R.layout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	LinearLayout arc;
	whaa wh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ��ȥ���⣨Ӧ�õ�����)
		// ���趨����Ҫд��setContentView֮ǰ����������쳣��

		setContentView(R.layout.canvas);
		
		arc = (LinearLayout)findViewById(R.id.arc);
		//arc.addView(new myView(this, 100));
		//mySurfaceView s = new mySurfaceView(this, 100);
		//arc.addView(new traversingRect(this));
		//arc.addView(s);
		wh = new whaa(this);
		arc.addView(wh);
		}
	@Override
	protected void onPause() {
		super.onPause();
		wh.pauseGame = true;
	};
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		wh.pauseGame = false;
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		wh.pauseGame = true;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		arc.removeView(wh);
		this.finish();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			AlertDialog isExit = new AlertDialog.Builder(this).create();
			isExit.setTitle("ϵͳ��ʾ!");
			isExit.setMessage("�Ƿ��˳���Ϸ!");
			isExit.setButton("ȷ��", listern);
			isExit.setButton2("ȡ��", listern);
			wh.pauseGame = true;
			isExit.show();
		}
		return false;
	}
	
	DialogInterface.OnClickListener listern =new OnClickListener() {
		
		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			switch(arg1){
			case AlertDialog.BUTTON_POSITIVE:
				finish();
				break;
			default:
				wh.pauseGame = false;
				break;
			}
		}
	};

}
