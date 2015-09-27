package com.smartcontroller.util;

public class DataPretreatment {

	/**
	 * ����Ԥ����
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
		// ��ά�����ǰ����Ϊ�Ƕȣ�������Ϊ���ٶ�
		double[] d = new double[12];

		// ���м��ٶ���ȡ
		double[] d1 = new AccelerationUtil().acceleration(input, r, c, 2);

		// ���нǶ���ȡ
		double[] d2 = new AngleUtil().angle(input, r, 3);

		// ����rms��ȡ
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
