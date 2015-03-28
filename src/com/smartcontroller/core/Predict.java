package com.smartcontroller.core;

import libsvm.svm;
import libsvm.svm_node;

import com.smartcontroller.util.Conf;
import com.smartcontroller.wifi.WifiCenter;

/**
 * Ԥ���̣߳�����Ԥ�⴫��������
 * 
 * @author Administrator
 * 
 */
public class Predict {

	/**
	 * ����Ԥ�����ݵ��߳�
	 * 
	 * @return
	 */
	public static Thread predict() {
		Thread th = null;
		System.out.println("........���빤��״̬����ʼ���в���Ԥ��..........");
		th = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// �ӻ���������ȡ����Ӧ���У�����Ԥ��
					for (int i = (Conf.temp_state - Conf.L), j = 0; i < (Conf.temp_state - Conf.R); i++, j++) {
						Conf.to_predict[j] = Conf.temp[i + 20 + 1];
					}
					// ����Ԥ��������и�ʽ��������������ֵ���е���
					double[] t = new double[(Conf.R - Conf.L)
							* Conf.FEATURE_NUM];

					for (int k = 0; k < Conf.FEATURE_NUM; k++) {
						for (int j = 0; j < Conf.R - Conf.L; j++) {
							t[j * Conf.FEATURE_NUM + k] = Conf.temp[j][k];
						}
					}
					// ������������node���飬����Ԥ��
					svm_node[] nodes = new svm_node[(Conf.R - Conf.L)
							* Conf.FEATURE_NUM];
					for (int i = 0; i < nodes.length; i++) {
						nodes[i] = new svm_node();
						nodes[i].index = i + 1;
						nodes[i].value = t[i];
					}
					// ����Ԥ��,�ó�Ԥ��ֵ
					double result = svm.svm_predict(Conf.MODEL, nodes);
					// ��������ڵ����еļҵ�㲥�õ��Ľ��ֵ
					WifiCenter.broadcast(result);
					try {
						Thread.sleep(Conf.predictTime);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
			}
		});

		return th;

	}
}
