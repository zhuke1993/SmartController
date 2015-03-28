package com.smartcontroller.wifi;

public class WifiCenter {
static String s=null;
	/**
	 * 向局域网内的所有家电广播预测结果
	 * 
	 * @param result
	 *            对动作数据进行预测的结果标签
	 */
	public static void broadcast(double result) {
	
		
		if(result==1.0){
			s="minglin1";
			//System.out.println("result is ...."+result);
			UDPBroadCast ubc=new UDPBroadCast(s);
			ubc.start();
	}
		System.out.println("向局域网内的家电进行广播预测结果：" + result);}

	/**
	 * 当家电的学习开关被触发的时候，向手机返回回其动作的标签值,当未接受到任何数据的时候，此方法保持阻塞状态，直到接收到数据为止
	 * 
	 * @return 某家电某动作的标签值
	 */
	public static String accept() {
		return UDPServer.flag;
		
	}
	
}
