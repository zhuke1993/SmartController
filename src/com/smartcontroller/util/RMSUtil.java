package com.smartcontroller.util;

public class RMSUtil {
	public static void main(String[] args) {
		double[][] input = new double[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				input[i][j] = i+1;
				System.out.print(input[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("++++++++");
		double[] d = new RMSUtil().rms(input, 3, 3);
		for (double i : d) {
			System.out.println(i);
		}
	}

	/**
	 * 均方根
	 * 
	 * @param input
	 *            传入的数据矩阵
	 * @param r
	 *            行
	 * @param c
	 *            列
	 * @return
	 */
	public double[] rms(double[][] input, int r, int c) {

		double[] d = new double[c];
		for (int i = 0; i < c; i++) {
			double temp = 0;
			for (int j = 0; j < r; j++) {
				temp = temp + input[j][i] * input[j][i];
			}
			d[i] = Math.sqrt(temp / r);
		}
		return d;
	}
}
