package com.smartcontroller.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * 此类是Android手机文件相关选项的工具类，初始化应用的相关文件以及文件夹
 * 
 * @author Administrator
 * 
 */
public class FileUtil {

	/**
	 * 将字符串添加到文件末尾
	 * 
	 * @param filePath
	 *            添加到的文件的文件路径
	 * @param addStr
	 *            待添加的字符串值
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
	 * 初始化应用所需的相关文件以及文件夹
	 * 
	 * @return
	 */
	public static boolean iniFile() {

		File tempFile = new File(Conf.action_f);
		File modelFile = new File(Conf.model_f);
		File outFile = new File(Conf.out_f);
		try {
			// 初始化应用文件夹
			File dirFile = new File(Conf.appDir);
			// 如果这是一个文件夹且不存在的话，则进行新建文件夹
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
