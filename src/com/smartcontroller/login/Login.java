package com.smartcontroller.login;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.smartcontroller.R;

public class Login extends Activity {
	public static final String URL = "http://zk929184318.eicp.net:8080/SmartControllerServer/login";
	Button submitBtnGet = null;
	Thread th = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				URL url = new URL(
						"http://zk929184318.eicp.net:8080/SmartControllerServer/login?phone=zhuke&pwd=zhuke");
				HttpURLConnection urlconn = (HttpURLConnection) url
						.openConnection();
				InputStream is = urlconn.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int len = -1;
				byte[] b = new byte[128];
				while ((len = is.read(b)) != -1) {
					baos.write(b, 0, len);
				}
				System.out.println(new String(baos.toByteArray()));
				Toast.makeText(getApplicationContext(), new String(baos.toByteArray()), Toast.LENGTH_SHORT).show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		Button submitBtnPost = (Button) findViewById(R.id.btn_submit_post);
		submitBtnPost.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				th.start();
			}
		});
	}
	
	

}
