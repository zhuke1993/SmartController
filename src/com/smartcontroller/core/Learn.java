package com.smartcontroller.core;

import java.io.IOException;

import libsvm.svm;
import libsvm.svm_problem;

import com.smartcontroller.entity.Param;
import com.smartcontroller.util.Conf;
import com.smartcontroller.util.FileToSvmProblem;
import com.smartcontroller.util.FileUtil;
import com.smartcontroller.wifi.UDPServer;
import com.smartcontroller.wifi.WifiCenter;


/**
 * 学习线程，在被触发时对触发前的动作数据进行学习
 * 
 * @author Administrator
 * 
 */
public class Learn {
	public static UDPServer us =null;
	
	public static Thread learn() {
		Thread th = new Thread(new Runnable() {
			
			@Override
			public void run() {
				//等待从wifi接收触发数据，当被触发的时候，学习线程解除阻塞，开始对新数据进行学习
				while(true){
					//接收到家电发来的数据
					String lable = WifiCenter.accept();
					if(!lable.equals("0")){
						System.out.println("学习的lable为:"+lable+"a");
						// 将待训练数组进行格式化处理，将各属性值进行调整
						double[] t = new double[(Conf.R - Conf.L) * Conf.FEATURE_NUM];
						
						for (int k = 0; k < Conf.FEATURE_NUM; k++) {
							for (int j = 0; j < Conf.R - Conf.L; j++) {
								t[j*Conf.FEATURE_NUM+k]=Conf.temp[j][k];
							}
						}
						System.out.println("\n");
						//将数据拼接成字符串，存入action.txt文件中，数据格式为 <lable>空格<attr1>:<value1>空格<attr2>:<value2>
						String addStr = lable+"";
						for (int i = 0; i < t.length; i++) {
							addStr = addStr+" "+(i+1) +":"+t[i];
						}
						//System.out.println("下列数据等待被学习：\n"+addStr);
						FileUtil.addStr2File(Conf.action_f, addStr+"\n");
						//System.out.println("addStr==========="+addStr);
						
						try {
							//对model进行再次训练
							svm_problem prob = FileToSvmProblem.read_problem(Conf.action_f, Param.setparameter(Conf.C, Conf.G));
							Conf.MODEL = svm.svm_train(prob, Param.setparameter(Conf.C, Conf.G));
							System.out.println(Conf.MODEL);
							//将最新的model保存进model_f.txt文件中
							svm.svm_save_model(Conf.model_f, Conf.MODEL);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		});
		
		return th;
		
	}
}
