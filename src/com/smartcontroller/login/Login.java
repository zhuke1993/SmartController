package com.smartcontroller.login;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smartcontroller.R;
import com.smartcontroller.core.Main;
import com.smartcontroller.util.Conf;

public class Login extends Activity {

	// 声明控件
	private EditText et_name, et_pass;
	private Button lg_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		// 获取控件对象
		et_name = (EditText) findViewById(R.id.phone); // 用户名控件
		et_pass = (EditText) findViewById(R.id.password);// 密码控件
		lg_btn = (Button) findViewById(R.id.login);
		lg_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login(v);
			}
		});
	}

	/**
	 * 按钮点击事件处理
	 * 
	 * @param v
	 */
	public void login(View v) {
		// 获取控件id
		int id = v.getId();

		// 获取控件的文本内容
		final String userName = et_name.getText().toString();// 用户名
		final String userPass = et_pass.getText().toString();// 用户密码
		// 判断用户名和密码是否为空
		if (TextUtils.isEmpty(userName.trim())
				|| TextUtils.isEmpty(userPass.trim())) {
			Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_LONG).show();
		} else {
			// 开启线程处理
			new Thread(new Runnable() {
				@Override
				public void run() {
					loginByHttpClientGet(userName, userPass);
				}
			}).start();
		}
	}

	/**
	 * 通过httpClient中的GET方式处理的
	 * 
	 * @param userName
	 * @param userPass
	 */
	public void loginByHttpClientGet(String userName, String userPass) {
		// HttpClient 发请求 GET方式处理
		// 1.创建 HttpClient 的实例 打开一个浏览器
		HttpClient client = new DefaultHttpClient(); // DefaultHttpClient
														// extends
														// AbstractHttpClient
		try {

			// 2. 创建某种连接方法的实例，在这里是HttpGet。在 HttpGet
			// 的构造函数中传入待连接的地址
			String uri = "http://zk929184318.eicp.net:8080/SmartControllerServer/login?phone="
					+ userName + "&pwd=" + userPass;
			// 强调 地址不能够出现 localhost:操作
			HttpGet httpGet = new HttpGet(uri);
			// 3. 调用第一步中创建好的实例的 execute 方法来执行第二步中创建好的 method 实例
			HttpResponse response = client.execute(httpGet); // 在浏览器中敲了一下回车
			// 4. 读 response
			int statusCode = response.getStatusLine().getStatusCode();// 读取状态行中的状态码
			if (statusCode == 200) { // 如果等于200 一切ok
				HttpEntity entity = response.getEntity();// 返回实体对象
				InputStream is = entity.getContent(); // 读取实体中内容
				int len = -1;
				byte[] b = new byte[128];
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				while ((len = is.read(b)) != -1) {
					baos.write(b, 0, len);
				}
				final String result = new String(baos.toByteArray());
				if (!result.equals("null")) {
					Login.this.runOnUiThread(new Runnable() { // 通过runOnUiThread方法处理
								@Override
								public void run() {
									Toast.makeText(getApplicationContext(),
											"登陆成功", Toast.LENGTH_LONG).show();
									Conf.lg_succ_user_id = result;
									Intent intent = new Intent();
									intent.setClass(getApplication(), Main.class);
									startActivity(intent);
								}
							});
				}else{
					Toast.makeText(getApplicationContext(),
							"登陆失败", Toast.LENGTH_LONG).show();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5.释放连接。无论执行方法是否成功，都必须释放连接
			client.getConnectionManager().shutdown();// 释放链接
		}
	}
}
