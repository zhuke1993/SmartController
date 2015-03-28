package com.smartcontroller.bluetooth;

import android.bluetooth.BluetoothSocket;

public class BluetoothCenter {

	public static BluetoothSocket btSocket;

	/**
	 * 蓝牙server
	 */
	public static BloothServer btserver;

	/**
	 * 此类需要完成的是，手机向手环发送一个字符数据s，主要用于唤醒手环向手机返回该时刻的状态值
	 * 
	 * @param s
	 *            需要发送的字符数据值
	 */
	public static void send(String s) {
		// 发送给手环的数据
		//System.out.println("手机向蓝牙发送数据：" + s);
		Bloothsend bs = new Bloothsend(btSocket, s);
		bs.start();
		
	}

	/**
	 * 手环返回该时刻的状态信息，为一个包含6个double的数据
	 * ，中间以空格隔开，听说已经是在手环中设定好了的，只要收到手机发送的r，手环就会自动返回，数据格式如下 -0.00308228
	 * -0.00506592 -0.309479 0.349579 0.159607 0.083252
	 * 
	 * @return 如上数据格式的一串字符串
	 */
	public static String accept() {

		String data = null;
		do {
			data= BloothServer.getData(btSocket);
		} while (data == null);
		//System.out.println("返回的数据是：" + data);
		return data;
	}
}
