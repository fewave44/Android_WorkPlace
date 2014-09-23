package com.example.blockmove;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	LinearLayout arc;
	GameSurfaceView gsv;
	private Handler handle;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐去标题（应用的名字)
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				  WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.canvas);
		
		context = this;
		handle = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				switch(msg.what)
				{
				case 0://开始界面
					
					break;
				case 1:
					arc.removeAllViews();
					gsv = new GameSurfaceView(context, handle);
					arc.addView(gsv);
					break;
				default:
					arc.removeAllViews();
					countScore cs = new countScore(context, handle, msg.getData().getInt("score")); 

					arc.addView(cs);
					break;				
				}
				super.handleMessage(msg);
			}
		};
		
		arc = (LinearLayout)findViewById(R.id.arc);
		gsv = new GameSurfaceView(context, handle);
		arc.addView(gsv);
		
	}
	@Override
	protected void onPause() {
		super.onPause();
		gsv.pauseGame = true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
			  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		gsv.pauseGame = false;
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		gsv.pauseGame = true;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		arc.removeView(gsv);
		this.finish();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{
			AlertDialog isExit = new AlertDialog.Builder(this).create();
			isExit.setTitle("系统提示!");
			isExit.setMessage("是否退出游戏!");
			isExit.setButton("确定", listern);
			isExit.setButton2("取消", listern);
			gsv.pauseGame = true;
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
				gsv.pauseGame = true;
				finish();
				break;
			default:
				gsv.pauseGame = false;
				break;
			}
		}
	};
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
