package com.smartcontroller.wifi;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

import android.R.integer;

public class UDPServer extends Thread {
	public static  String flag="0";
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		while(true){
			try {
				receiveIP();
				//System.out.println("udpserver��������");
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
			  System.out.println("���յ�������Ϊ��"+ UDPServer.flag);
			  dgSocket.close();
		} catch (Exception e) {
			//System.out.println("δ���ܵ���������");
			UDPServer.flag = "0";
		}
		 
	 }

}
