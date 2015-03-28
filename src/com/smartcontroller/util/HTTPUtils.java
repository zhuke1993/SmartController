package com.smartcontroller.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HTTPUtils {
	public static void main(String[] args) {
		System.out.println(HTTPUtils.doGet());
	}
	
	public static String doGet(){
		try {
			URL url = new URL(Conf.uri+"/login?phone=zhuke&pwd=zhuke");
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
