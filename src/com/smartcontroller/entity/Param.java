package com.smartcontroller.entity;

import libsvm.svm_parameter;

/**
 * ������svm�ĸ����������
 * 
 * @author Administrator
 * 
 */
public class Param {

	/**
	 * svm��������
	 * 
	 * @param c
	 * @param g
	 * @return
	 */
	public static svm_parameter setparameter(double c, double g) {
		svm_parameter param = new svm_parameter();
		param.svm_type = svm_parameter.C_SVC;
		param.kernel_type = svm_parameter.RBF;
		param.degree = 3;
		param.gamma = g;
		param.coef0 = 0;
		param.nu = 0.5;
		param.cache_size = 100;
		param.C = c;
		param.eps = 0.001;
		param.p = 0.1;
		param.shrinking = 1;
		param.probability = 0;
		param.nr_weight = 0;
		param.weight_label = null;
		param.weight = null;
		return param;
	}
}
