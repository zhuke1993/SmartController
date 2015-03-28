package com.smartcontroller.util;

import java.util.StringTokenizer;

import libsvm.svm_problem;

/**
 * ���������յ������ݽ��и�ʽ������
 * 
 * @author Administrator
 * 
 */
public class DataFormat {
	
	
	/**
	 * ���ַ���ת��Ϊsvm_problem�����ַ�����ʽΪlabel1 index1:value1 index2:value2 
	 * @param s	������ַ�������ʽΪlabel1 index1:value1 index2:value2 
	 * @return	�����svm_problem
	 */
	public static svm_problem string2Svm_prob(String s){
		
		return null;
	}
	

	/**
	 * ��ĳʱ���ֻ���״̬��Ϣ���и�ʽ���͹�һ������
	 * @param s  �ֻ���״̬��Ϣ������Ϊһ���ַ�������ʽΪ-0.00308228 -0.00506592 -0.309479 0.349579 0.159607 0.083252
	 * @return  �������������й�һ������󣬴��һ��double�����У����ظ�double����
	 */
	public static double[] format(String s) {
		// �����Կո���зָ�
		StringTokenizer str = new StringTokenizer(s, " ");
		int len = str.countTokens();
		double [] d = new double[len];
		if (s != null) {
			// �������ݸ�ʽ���и���ʽ������
			for (int i = 0; i < len; i++) {
				double temp = Double.parseDouble(str.nextToken());
				
				//�����ݽ��й�һ������
				temp = (temp + 32768) / 32768 - 1;
				d[i] = temp;
			}
		}
		return d;

	}

}
