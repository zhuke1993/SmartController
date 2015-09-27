package com.smartcontroller.util;


public class Sjwlsf {
	static int IN_MUN = 12; // 输入层神经元数目
	static int HideN = 10; // 隐层神经元数目，前馈网络隐藏层
	static int OutN = 7; // 输出层神经元数模，各个命令的开关阀
	static int N = 7;
	static double Weight_In_Hide[][] = new double[HideN][IN_MUN]; // 输入层至隐层权值
	static double V_Hide_Out[][] = new double[OutN][HideN]; // 隐层至输出层权值
	static double YU_HN[] = new double[HideN]; // 隐层的阈值
	static double YU_ON[] = new double[OutN]; // 输出层的阈值
	static double X[] = new double[HideN]; // 隐层的输入,各个隐藏层的神经单元的内积
	static double Y[] = new double[OutN]; // 输出层的输入，各个输出层的神经单元的内积
	static double H[] = new double[HideN]; // 隐层的输出，tansig函数之后的输出值
	static double O[] = new double[OutN]; // 输出层的输出，softmax函数之后的输出值

	static double input[] = new double[IN_MUN]; // 学生样本

	public static void main(String[] args) {
		double sigma1, sigma2;
		for (int i = 0; i < HideN; i++) {
			sigma1 = 0.0f;
			/* 求隐层内积 */
			for (int j = 0; j < IN_MUN; j++)
				sigma1 = sigma1 + Weight_In_Hide[i][j] * input[j];

			X[i] = sigma1 + YU_HN[i]; /* 权值*输入值 再 加上阈值 */
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
