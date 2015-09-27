package com.smartcontroller.util;

public class AngleUtil {

	/**
	 * �Ƕȴ���
	 * 
	 * @param input
	 *            ��������ݾ���
	 * @param r
	 *            ��
	 * @param c
	 *            ��
	 * @return
	 */
	public double[] angle(double[][] input, int r, int c) {

		double[] d = new double[c];
		for (int i = 0; i < c; i++) {
			double temp = 0;
			for (int j = 0; j < r; j++) {
				temp = temp + input[j][i];
			}
			d[i] = temp;
		}
		return d;
	}

}
