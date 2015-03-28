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
 * ѧϰ�̣߳��ڱ�����ʱ�Դ���ǰ�Ķ������ݽ���ѧϰ
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
				//�ȴ���wifi���մ������ݣ�����������ʱ��ѧϰ�߳̽����������ʼ�������ݽ���ѧϰ
				while(true){
					//���յ��ҵ緢��������
					String lable = WifiCenter.accept();
					if(!lable.equals("0")){
						System.out.println("ѧϰ��lableΪ:"+lable+"a");
						// ����ѵ��������и�ʽ��������������ֵ���е���
						double[] t = new double[(Conf.R - Conf.L) * Conf.FEATURE_NUM];
						
						for (int k = 0; k < Conf.FEATURE_NUM; k++) {
							for (int j = 0; j < Conf.R - Conf.L; j++) {
								t[j*Conf.FEATURE_NUM+k]=Conf.temp[j][k];
							}
						}
						System.out.println("\n");
						//������ƴ�ӳ��ַ���������action.txt�ļ��У����ݸ�ʽΪ <lable>�ո�<attr1>:<value1>�ո�<attr2>:<value2>
						String addStr = lable+"";
						for (int i = 0; i < t.length; i++) {
							addStr = addStr+" "+(i+1) +":"+t[i];
						}
						//System.out.println("�������ݵȴ���ѧϰ��\n"+addStr);
						FileUtil.addStr2File(Conf.action_f, addStr+"\n");
						//System.out.println("addStr==========="+addStr);
						
						try {
							//��model�����ٴ�ѵ��
							svm_problem prob = FileToSvmProblem.read_problem(Conf.action_f, Param.setparameter(Conf.C, Conf.G));
							Conf.MODEL = svm.svm_train(prob, Param.setparameter(Conf.C, Conf.G));
							System.out.println(Conf.MODEL);
							//�����µ�model�����model_f.txt�ļ���
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
