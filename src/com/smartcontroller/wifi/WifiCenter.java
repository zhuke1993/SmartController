package com.smartcontroller.wifi;

public class WifiCenter {
static String s=null;
	/**
	 * ��������ڵ����мҵ�㲥Ԥ����
	 * 
	 * @param result
	 *            �Զ������ݽ���Ԥ��Ľ����ǩ
	 */
	public static void broadcast(double result) {
	
		
		if(result==1.0){
			s="minglin1";
			//System.out.println("result is ...."+result);
			UDPBroadCast ubc=new UDPBroadCast(s);
			ubc.start();
	}
		System.out.println("��������ڵļҵ���й㲥Ԥ������" + result);}

	/**
	 * ���ҵ��ѧϰ���ر�������ʱ�����ֻ����ػ��䶯���ı�ǩֵ,��δ���ܵ��κ����ݵ�ʱ�򣬴˷�����������״̬��ֱ�����յ�����Ϊֹ
	 * 
	 * @return ĳ�ҵ�ĳ�����ı�ǩֵ
	 */
	public static String accept() {
		return UDPServer.flag;
		
	}
	
}
