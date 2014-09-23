package com.liukai.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MessageActivity extends Activity {
	MediaRecorder recorder = new MediaRecorder();

	class SendButtonOnTouchListener implements OnTouchListener {

		String filename = "";

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			if (event.getAction() == 0) {

				filename = new Date().getTime() + ".amr";
				recorder.reset();
				recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
				// 从麦克风采集声音
				recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				// 内容输出格式
				recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				// 音频编码方式

				recorder.setOutputFile("/sdcard/" + filename);// 记住开SD权限
				try {
					recorder.prepare();
				} catch (Exception e) {
					Toast.makeText(MessageActivity.this, "请在试一次!", 1000).show();
				}// 预期准备
				recorder.start(); // 开始刻录
			} else if (event.getAction() == 1) {
				recorder.stop();// 停止刻录
				recorder.reset(); // 重设

				LinearLayout client = new LinearLayout(MessageActivity.this);
				TextView t = new TextView(MessageActivity.this);
				t.setText("我说:");
				Button button = new Button(MessageActivity.this);
				button.setText("播放");
				final String filepath = filename;
				button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						MediaPlayer mediaPlayer = new MediaPlayer();
						if (mediaPlayer.isPlaying()) {
							mediaPlayer.reset();// 重置为初始状态
						}
						try {
							mediaPlayer.setDataSource("/sdcard/" + filepath);
							mediaPlayer.prepare();// 缓冲
							mediaPlayer.start();// 开始或恢复播放
						} catch (Exception e) {
							// TODO: handle exception
						}

					}
				});
				client.setOrientation(LinearLayout.HORIZONTAL);
				client.addView(t);
				client.addView(button);
				line.addView(client);

				try {

					Socket socket = new Socket("10.3.128.169",5555);
					InputStream in = socket.getInputStream();
					OutputStream out = socket.getOutputStream();
					out.write(("upload,"
									+ new File("/sdcard/" + filename).length()
									+ "," + MainActivity.uname).getBytes());
					out.flush();
					byte[] b = new byte[10];
					in.read(b);
					FileInputStream fin = new FileInputStream("/sdcard/"
							+ filename);
					int len = 0;
					byte[] b1 = new byte[1024];
					while ((len = fin.read(b1)) != -1) {
						out.write(b1, 0, len);
						out.flush();
					}
					fin.close();
					socket.close();

				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			return false;
		}

	}

	LinearLayout line = null;
	Handler han = new Handler() {

		public void handleMessage(android.os.Message msg) {

			LinearLayout client = new LinearLayout(MessageActivity.this);
			TextView t = new TextView(MessageActivity.this);
			t.setText(msg.getData().getString("username") + "说:");
			Button button = new Button(MessageActivity.this);
			button.setText("播放");
			final String filepath = msg.getData().getString("filename");
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					MediaPlayer mediaPlayer = new MediaPlayer();
					if (mediaPlayer.isPlaying()) {
						mediaPlayer.reset();// 重置为初始状态
					}
					try {
						mediaPlayer.setDataSource("/sdcard/" + filepath);
						mediaPlayer.prepare();// 缓冲
						mediaPlayer.start();// 开始或恢复播放
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			});
			client.setOrientation(LinearLayout.HORIZONTAL);
			client.addView(t);
			client.addView(button);

			line.addView(client);

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.message);
		Button sendButton = (Button) findViewById(R.id.sendButton);
		line = (LinearLayout) findViewById(R.id.list_linearLayout);
		sendButton.setOnTouchListener(new SendButtonOnTouchListener());

		new Thread() {
			public void run() {

				UDPServer.message=MessageActivity.this;
				try {
					UDPServer.openServer();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};

		}.start();

	}

	int count = 0;

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		if (count == 1) {
			System.exit(0);
		}
		if (event.KEYCODE_BACK == keyCode) {
			Toast.makeText(this, "你有本事再按一次", 1000).show();
			count++;
			return false;
		}
		// TODO Auto-generated method stub
		return super.onKeyUp(keyCode, event);
	}

}
