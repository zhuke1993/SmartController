package com.smartcontroller.util;

public class DataPretreatment {

	/**
	 * 数据预处理
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
		// 二维数组的前三列为角度，后三列为加速度
		double[] d = new double[12];

		// 进行加速度提取
		double[] d1 = new AccelerationUtil().acceleration(input, r, c, 2);

		// 进行角度提取
		double[] d2 = new AngleUtil().angle(input, r, 3);

		// 进行rms提取
		double[] d3 = new RMSUtil().rms(input, r, c);

		// rms+angle+acc
		for (int i = 0; i < 12; i++) {
			int j, k, l;
			for (j = 0; j < d3.length; j++) {
				d[i] = d3[j];
				i++;
			}
			for (k = 0; k < d2.length; k++) {
				d[i] = d2[k];
				i++;
			}
			for (l = 0; l < d1.length; l++) {
				d[i] = d1[l];
				i++;
			}
		}

		return d;
	}
}
