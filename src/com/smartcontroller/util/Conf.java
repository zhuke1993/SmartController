package com.smartcontroller.util;

import libsvm.svm_model;
import android.os.Environment;

/**
 * 包含软件的各类配置信息
 * 
 * @author Administrator
 * 
 */
public class Conf {
	
	/**
	 * 远程服务器的uri地址
	 */
	public static String uri="http://zk929184318.eicp.net:8080/SmartControllerServer";


	/**
	 * 应用的主文件夹目录
	 */
	public static String appDir = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + "/SmartController";
	
	
	/**
	 * 常量model
	 */
	public static svm_model MODEL = new svm_model();
	
	/**
	 * 手机需要每隔多长得时间向手环发送一个字符“r”，手环返回回该时刻的状态值,单位为ms（1s = 1000ms）
	 */
	public static int actionTime = 2000;
	
	/**
	 * 预测线程执行的时间片
	 */
	public static int predictTime = 200;
	
	

	/**
	 * 预置的动作数据
	 */
	public static String init_actioin_f = appDir + "/initAction.txt";

	/**
	 * 存放被训练的动作数据，数据格式为 <lable>空格<attr1>:<value1>空格<attr2>:<value2>一共60组数据
	 */
	public static String action_f = appDir + "/action_f.txt";

	/**
	 * 是否立即对接收到的数据进行训练，测试模式下为立即对model进行训练
	 * 
	 * 0-立即训练 1-不立即训练，而是存入到temp.txt文件中，等待系统调用
	 */
	public static int train_flag = 0;

	/**
	 * 应用的model文件存放路径
	 */
	public static String model_f = appDir + "/model.txt";

	/**
	 * 生成的结果的文件的路径
	 */
	public static String out_f = appDir + "/out.txt";

	/**
	 * 特征数
	 */
	public static int FEATURE_NUM = 6;


	/**
	 * 记录前2s的动作
	 */
	public static int ACTION_TO_RECORD = 20;

	public static int MAX_TRIGGER_NUM = 20;
	
	/**
	 * 用来记录前2s的时间内，手环的动作信息状态，当数组满时，会覆盖最旧的数据，维护一个20*6的数组
	 * 数组中的每一行的数据都已经进行了归一化处理，且数据格式为ax ay az bx by bz
	 */
	public static double[][] temp = new double[ACTION_TO_RECORD][FEATURE_NUM];
	
	/**
	 * 从预测数组中取出数据行数的左边界，以当前时刻为时间截点
	 */
	public static int L = -20;
	
	/**
	 * 从预测数组中取出数据行数的右边界，以当前时刻为时间截点
	 */
	public static int R = -10;
	
	/**
	 * 设置一个temp_state来监视temp数组的情况，即最新的数据到达了哪一行
	 */
	public static int temp_state = 0;
	
	/**
	 * 用来记录待预测的数据的数组
	 */
	public static double[][] to_predict = new double[R-L][FEATURE_NUM];
	
	
	/**
	 * svm_parameter的c的值
	 */
	public static double C = 6;
	
	/**
	 * svm_parameter的gamma的值
	 */
	public static double G = 0.01;
	
	
}
