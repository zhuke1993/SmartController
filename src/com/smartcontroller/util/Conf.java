package com.smartcontroller.util;

import libsvm.svm_model;
import android.os.Environment;

/**
 * ��������ĸ���������Ϣ
 * 
 * @author Administrator
 * 
 */
public class Conf {
	
	/**
	 * Զ�̷�������uri��ַ
	 */
	public static String uri="http://zk929184318.eicp.net:8080/SmartControllerServer";


	/**
	 * Ӧ�õ����ļ���Ŀ¼
	 */
	public static String appDir = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/SmartController";
	
	
	/**
	 * ����model
	 */
	public static svm_model MODEL = new svm_model();
	
	/**
	 * �ֻ���Ҫÿ���೤��ʱ�����ֻ�����һ���ַ���r�����ֻ����ػظ�ʱ�̵�״ֵ̬,��λΪms��1s = 1000ms��
	 */
	public static int actionTime = 2000;
	
	/**
	 * Ԥ���߳�ִ�е�ʱ��Ƭ
	 */
	public static int predictTime = 200;
	
	

	/**
	 * Ԥ�õĶ�������
	 */
	public static String init_actioin_f = appDir + "/initAction.txt";

	/**
	 * ��ű�ѵ���Ķ������ݣ����ݸ�ʽΪ <lable>�ո�<attr1>:<value1>�ո�<attr2>:<value2>һ��60������
	 */
	public static String action_f = appDir + "/action_f.txt";

	/**
	 * �Ƿ������Խ��յ������ݽ���ѵ��������ģʽ��Ϊ������model����ѵ��
	 * 
	 * 0-����ѵ�� 1-������ѵ�������Ǵ��뵽temp.txt�ļ��У��ȴ�ϵͳ����
	 */
	public static int train_flag = 0;

	/**
	 * Ӧ�õ�model�ļ����·��
	 */
	public static String model_f = appDir + "/model.txt";

	/**
	 * ���ɵĽ�����ļ���·��
	 */
	public static String out_f = appDir + "/out.txt";

	/**
	 * ������
	 */
	public static int FEATURE_NUM = 6;


	/**
	 * ��¼ǰ2s�Ķ���
	 */
	public static int ACTION_TO_RECORD = 20;

	public static int MAX_TRIGGER_NUM = 20;
	
	/**
	 * ������¼ǰ2s��ʱ���ڣ��ֻ��Ķ�����Ϣ״̬����������ʱ���Ḳ����ɵ����ݣ�ά��һ��20*6������
	 * �����е�ÿһ�е����ݶ��Ѿ������˹�һ�����������ݸ�ʽΪax ay az bx by bz
	 */
	public static double[][] temp = new double[ACTION_TO_RECORD][FEATURE_NUM];
	
	/**
	 * ��Ԥ��������ȡ��������������߽磬�Ե�ǰʱ��Ϊʱ��ص�
	 */
	public static int L = -20;
	
	/**
	 * ��Ԥ��������ȡ�������������ұ߽磬�Ե�ǰʱ��Ϊʱ��ص�
	 */
	public static int R = -10;
	
	/**
	 * ����һ��temp_state������temp���������������µ����ݵ�������һ��
	 */
	public static int temp_state = 0;
	
	/**
	 * ������¼��Ԥ������ݵ�����
	 */
	public static double[][] to_predict = new double[R-L][FEATURE_NUM];
	
	
	/**
	 * svm_parameter��c��ֵ
	 */
	public static double C = 6;
	
	/**
	 * svm_parameter��gamma��ֵ
	 */
	public static double G = 0.01;
	
	
}
