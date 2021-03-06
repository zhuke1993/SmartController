package com.sc.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTest {
	public static void main(String[] args) throws IOException {
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
	}
	
	public static String doGet(){
		try {
			URL url = new URL("http://zk929184318.eicp.net:8080/SmartControllerServer/login?phone=zhuke&pwd=zhuke");
			HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();
			InputStream is = urlconn.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = -1;
			byte [] b = new byte[128];
			while((len= is.read(b))!= -1){
				baos.write(b, 0, len);
			}
			return new String(baos.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
