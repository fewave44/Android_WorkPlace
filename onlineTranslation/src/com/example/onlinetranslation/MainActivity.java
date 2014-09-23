package com.example.onlinetranslation;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.*;

public class MainActivity extends ActionBarActivity {

	private Handler handler;
	private TextView txtOut;
	private Button btnOK;
	private EditText txtInput;
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtOut = (TextView)findViewById(R.id.txtOut);
		btnOK = (Button)findViewById(R.id.btnOK);
		txtInput =(EditText)findViewById(R.id.input);
		context = this; 
		
		handler = new Handler() {
			
			@Override
			public void handleMessage(Message arg0) {
				// TODO Auto-generated method stub
				if(arg0 == null)
					return;
				Bundle data = arg0.getData();
				txtOut.setText(data.getString("Val"));
				super.handleMessage(arg0);
			}
			
		};
		btnOK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				txtOut.setText(" ");
				if(txtInput.getText().length() == 0)
				{
					Toast.makeText(context, R.string.input_error, 1);
					return;
				}				
				Translate translate = new Translate(context, handler, txtInput.getText().toString().trim());
				translate.start();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

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


}
