package com.smartcontroller.util;

public class AccelerationUtil {

	/**
	 * 加速度处理
	 * 
	 * @param input
	 *            传入的数据矩阵
	 * @param r
	 *            行
	 * @param c
	 *            列
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
