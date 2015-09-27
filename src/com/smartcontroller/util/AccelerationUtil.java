package com.smartcontroller.util;

public class AccelerationUtil {

	/**
	 * ���ٶȴ���
	 * 
	 * @param input
	 *            ��������ݾ���
	 * @param r
	 *            ��
	 * @param c
	 *            ��
	 * @return
	 */
	public double[] acceleration(double[][] input, int r, int c ,int beginC) {

		double[] d = new double[c];
		for (int i = 0; i < c; i++) {
			double temp = 0;
			for (int j = beginC; j < r; j++) {
				temp = temp + input[j][i]
						* (Math.pow(((double) (j + 1)) / 10, 2));
			}
			d[i] = temp;
		}
		return d;
	}
}
