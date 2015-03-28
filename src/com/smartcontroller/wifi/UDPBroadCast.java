package com.smartcontroller.wifi;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPBroadCast extends Thread {
	String mingling = null;

	public UDPBroadCast(String s) {
		// TODO 自动生成的构造函数存根
		this.mingling = s;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
			BroadcastIP();
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.run();
	}

	private void BroadcastIP() throws Exception {
		DatagramSocket dgSocket = new DatagramSocket();
		byte b[] = mingling.getBytes();
		System.out.println("发送的数据是"+mingling);
		DatagramPacket dgPacket = new DatagramPacket(b, b.length,
				InetAddress.getByName("255.255.255.255"), 5001);
		dgSocket.send(dgPacket);
		dgSocket.close();

	}

}
