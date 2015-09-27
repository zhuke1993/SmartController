package com.smartcontroller.util;

public class AngleUtil {

	/**
	 * 角度处理
	 * 
	 * @param input
	 *            传入的数据矩阵
	 * @param r
	 *            行
	 * @param c
	 *            列
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
