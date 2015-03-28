package com.smartcontroller.wifi;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import android.R.integer;

public class UDPServer extends Thread {
	public static  String flag="0";
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		while(true){
			try {
				receiveIP();
				//System.out.println("udpserver正常运行");
			} catch (Exception e) {
				//e.printStackTrace();
			}
		super.run();
		}
		
	}
	private void receiveIP(){
		try {
			 DatagramSocket dgSocket=new DatagramSocket(5431);
			  byte[] by=new byte[1024];
			  DatagramPacket packet=new DatagramPacket(by,by.length);
			  dgSocket.receive(packet);		  
			  UDPServer.flag=new String(packet.getData(),0,packet.getLength());
			  System.out.println("接收到的数据为："+ UDPServer.flag);
			  dgSocket.close();
		} catch (Exception e) {
			//System.out.println("未接受到触发数据");
			UDPServer.flag = "0";
		}
		 
	 }

}
