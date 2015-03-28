package com.smartcontroller.core;

import libsvm.svm;
import libsvm.svm_node;

import com.smartcontroller.util.Conf;
import com.smartcontroller.wifi.WifiCenter;

/**
 * 预测线程，不断预测传来的数据
 * 
 * @author Administrator
 * 
 */
public class Predict {

	/**
	 * 不断预测数据的线程
	 * 
	 * @return
	 */
	public static Thread predict() {
		Thread th = null;
		System.out.println("........进入工作状态，开始进行不断预测..........");
		th = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					// 从缓存数组中取出相应的行，进行预测
					for (int i = (Conf.temp_state - Conf.L), j = 0; i < (Conf.temp_state - Conf.R); i++, j++) {
						Conf.to_predict[j] = Conf.temp[i + 20 + 1];
					}
					// 将待预测数组进行格式化处理，将各属性值进行调整
					double[] t = new double[(Conf.R - Conf.L)
							* Conf.FEATURE_NUM];

					for (int k = 0; k < Conf.FEATURE_NUM; k++) {
						for (int j = 0; j < Conf.R - Conf.L; j++) {
							t[j * Conf.FEATURE_NUM + k] = Conf.temp[j][k];
						}
					}
					// 将该数组填充进node数组，进行预测
					svm_node[] nodes = new svm_node[(Conf.R - Conf.L)
							* Conf.FEATURE_NUM];
					for (int i = 0; i < nodes.length; i++) {
						nodes[i] = new svm_node();
						nodes[i].index = i + 1;
						nodes[i].value = t[i];
					}
					// 进行预测,得出预测值
					double result = svm.svm_predict(Conf.MODEL, nodes);
					// 向局域网内的所有的家电广播得到的结果值
					WifiCenter.broadcast(result);
					try {
						Thread.sleep(Conf.predictTime);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
		});

		return th;

	}
}
