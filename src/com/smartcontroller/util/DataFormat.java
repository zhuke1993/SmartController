package com.smartcontroller.util;

import java.util.StringTokenizer;

import libsvm.svm_problem;

/**
 * 将蓝牙接收到的数据进行格式化处理
 * 
 * @author Administrator
 * 
 */
public class DataFormat {
	
	
	/**
	 * 将字符串转化为svm_problem对象，字符串格式为label1 index1:value1 index2:value2 
	 * @param s	传入的字符串，格式为label1 index1:value1 index2:value2 
	 * @return	填充后的svm_problem
	 */
	public static svm_problem string2Svm_prob(String s){
		
		return null;
	}
	

	/**
	 * 对某时刻手环的状态信息进行格式化和归一化处理
	 * @param s  手环的状态信息，数据为一个字符串，格式为-0.00308228 -0.00506592 -0.309479 0.349579 0.159607 0.083252
	 * @return  将各动作量进行归一化处理后，存进一个double数组中，返回该double数组
	 */
	public static double[] format(String s) {
		// 数据以空格进行分割
		StringTokenizer str = new StringTokenizer(s, " ");
		int len = str.countTokens();
		double [] d = new double[len];
		if (s != null) {
			// 按照数据格式进行个格式化处理
			for (int i = 0; i < len; i++) {
				double temp = Double.parseDouble(str.nextToken());
				
				//将数据进行归一化处理
				temp = (temp + 32768) / 32768 - 1;
				d[i] = temp;
			}
		}
		return d;

	}

}
