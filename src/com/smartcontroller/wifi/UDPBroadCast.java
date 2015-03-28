package com.smartcontroller.wifi;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPBroadCast extends Thread {
	String mingling = null;

	public UDPBroadCast(String s) {
		// TODO �Զ����ɵĹ��캯�����
		this.mingling = s;
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
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
		System.out.println("���͵�������"+mingling);
		DatagramPacket dgPacket = new DatagramPacket(b, b.length,
				InetAddress.getByName("255.255.255.255"), 5001);
		dgSocket.send(dgPacket);
		dgSocket.close();

	}

}
