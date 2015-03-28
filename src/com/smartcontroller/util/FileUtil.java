package com.smartcontroller.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * ������Android�ֻ��ļ����ѡ��Ĺ����࣬��ʼ��Ӧ�õ�����ļ��Լ��ļ���
 * 
 * @author Administrator
 * 
 */
public class FileUtil {

	/**
	 * ���ַ�����ӵ��ļ�ĩβ
	 * 
	 * @param filePath
	 *            ��ӵ����ļ����ļ�·��
	 * @param addStr
	 *            ����ӵ��ַ���ֵ
	 */
	public static void addStr2File(String filePath, String addStr) {
		try {
			BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath),true)));
			bufw.append(addStr);
			bufw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ʼ��Ӧ�����������ļ��Լ��ļ���
	 * 
	 * @return
	 */
	public static boolean iniFile() {

		File tempFile = new File(Conf.action_f);
		File modelFile = new File(Conf.model_f);
		File outFile = new File(Conf.out_f);
		try {
			// ��ʼ��Ӧ���ļ���
			File dirFile = new File(Conf.appDir);
			// �������һ���ļ����Ҳ����ڵĻ���������½��ļ���
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			if (!tempFile.exists()) {
				tempFile.createNewFile();
			}
			if (!modelFile.exists()) {
				modelFile.createNewFile();
			}
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
