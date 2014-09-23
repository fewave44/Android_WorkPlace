package com.liukai.app;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	/** Called when the activity is first created. */
	Button loginButton = null;
	Button exitButton = null;
	EditText usernameEditText = null;
	EditText passwordEditText = null;
	static String uname="";

	class LoginClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			String username = usernameEditText.getText().toString();
			String password = passwordEditText.getText().toString();
			uname=username;
			String netMessage = "login," + username + "," + password;
       
			try {
				Socket socket = new Socket("10.3.128.169",5555);
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();

				out.write(netMessage.getBytes());
				out.flush();
				byte[] b = new byte[1024];
				in.read(b);
				if (new String(b).trim().equalsIgnoreCase("OK")) {
					Toast.makeText(MainActivity.this, "登录成功！", 1000).show();
					Intent in1 = new Intent();
					in1.setClass(MainActivity.this, MessageActivity.class);
					startActivity(in1);
					finish();
				} else {
					Toast.makeText(MainActivity.this, "用户名或密码错误！", 1000).show();
				}
				socket.close();
			} catch (Exception e) {
				Toast.makeText(MainActivity.this, "网络异常！", 1000).show();
			}

		}

	}

	class ExitClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			System.exit(0);

		}

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		loginButton = (Button) findViewById(R.id.login_button);
		exitButton = (Button) findViewById(R.id.exit_button);
		usernameEditText = (EditText) findViewById(R.id.Username_editText);
		passwordEditText = (EditText) findViewById(R.id.Password_editText);

		loginButton.setOnClickListener(new LoginClickListener());
		exitButton.setOnClickListener(new ExitClickListener());

	}
}