package com.smartcontroller.util;


public class Sjwlsf {
	static int IN_MUN = 12; // �������Ԫ��Ŀ
	static int HideN = 10; // ������Ԫ��Ŀ��ǰ���������ز�
	static int OutN = 7; // �������Ԫ��ģ����������Ŀ��ط�
	static int N = 7;
	static double Weight_In_Hide[][] = new double[HideN][IN_MUN]; // �����������Ȩֵ
	static double V_Hide_Out[][] = new double[OutN][HideN]; // �����������Ȩֵ
	static double YU_HN[] = new double[HideN]; // �������ֵ
	static double YU_ON[] = new double[OutN]; // ��������ֵ
	static double X[] = new double[HideN]; // ���������,�������ز���񾭵�Ԫ���ڻ�
	static double Y[] = new double[OutN]; // ���������룬�����������񾭵�Ԫ���ڻ�
	static double H[] = new double[HideN]; // ����������tansig����֮������ֵ
	static double O[] = new double[OutN]; // �����������softmax����֮������ֵ

	static double input[] = new double[IN_MUN]; // ѧ������

	public static void main(String[] args) {
		double sigma1, sigma2;
		for (int i = 0; i < HideN; i++) {
			sigma1 = 0.0f;
			/* �������ڻ� */
			for (int j = 0; j < IN_MUN; j++)
				sigma1 = sigma1 + Weight_In_Hide[i][j] * input[j];

			X[i] = sigma1 + YU_HN[i]; /* Ȩֵ*����ֵ �� ������ֵ */
			H[i] = (2.0 / (1.0 + Math.exp(-2 * X[i])) - 1); /*
															 * tansig(n) =
															 * 2/(1+exp(-2*n))-1
															 */
		}

		double sum = 0;
		for (int i = 0; i < Y.length; i++) {
			sum = sum + Math.exp(Y[i]);
		}

		for (int ii = 0; ii < OutN; ii++) {
			sigma2 = 0.0f;
			for (int jj = 0; jj < HideN; jj++)
				sigma2 = sigma2 + V_Hide_Out[ii][jj] * H[jj];

			Y[ii] = sigma2 + YU_ON[ii];
			O[ii] = Math.exp(Y[ii]) / sum; /*
											 * softmax(n) = d * exp(n
											 * )/sum(exp(n)))
											 */
		}

	}
}
