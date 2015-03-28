package com.smartcontroller.bluetooth;

import android.bluetooth.BluetoothSocket;

public class BluetoothCenter {

	public static BluetoothSocket btSocket;

	/**
	 * ����server
	 */
	public static BloothServer btserver;

	/**
	 * ������Ҫ��ɵ��ǣ��ֻ����ֻ�����һ���ַ�����s����Ҫ���ڻ����ֻ����ֻ����ظ�ʱ�̵�״ֵ̬
	 * 
	 * @param s
	 *            ��Ҫ���͵��ַ�����ֵ
	 */
	public static void send(String s) {
		// ���͸��ֻ�������
		//System.out.println("�ֻ��������������ݣ�" + s);
		Bloothsend bs = new Bloothsend(btSocket, s);
		bs.start();
		
	}

	/**
	 * �ֻ����ظ�ʱ�̵�״̬��Ϣ��Ϊһ������6��double������
	 * ���м��Կո��������˵�Ѿ������ֻ����趨���˵ģ�ֻҪ�յ��ֻ����͵�r���ֻ��ͻ��Զ����أ����ݸ�ʽ���� -0.00308228
	 * -0.00506592 -0.309479 0.349579 0.159607 0.083252
	 * 
	 * @return �������ݸ�ʽ��һ���ַ���
	 */
	public static String accept() {

		String data = null;
		do {
			data= BloothServer.getData(btSocket);
		} while (data == null);
		//System.out.println("���ص������ǣ�" + data);
		return data;
	}
}
