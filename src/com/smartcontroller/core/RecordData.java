package com.smartcontroller.core;

import com.smartcontroller.bluetooth.BluetoothCenter;
import com.smartcontroller.util.Conf;
import com.smartcontroller.util.DataFormat;

/**
 * 数据记录线程，不断接收从手环蓝牙传送过来的数据
 * 
 * @author Administrator
 * 
 */
public class RecordData {
	/**
	 * 数据记录线程，不断接收从手环蓝牙传送过来的数据
	 * 
	 * @return 数据记录线程
	 */
	public static Thread recordData() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				BluetoothCenter.btserver.start();
				while (true) {
					try {
						String acc = null;
						// 工作主要状态，需要通过蓝牙定时向蓝牙发送数据r，得到蓝牙返回回来的该状态的状态属性
						BluetoothCenter.send("r");
						//System.out.println("向手环发送了一个动作值r");
						// 接收蓝牙返回回的一个状态信息，包含6个double值的string，中间以空格隔开了
						acc = BluetoothCenter.accept();

						System.out.println("从手环得到了其此时的状态值:" + acc);
						
						// 对数据进行归一化处理，并将其存入二维数组中
						double[] d = DataFormat.format(acc);

						// Conf.temp_state自增
						Conf.temp_state++;
						// temp数组为一个循环队列，当temp_state的值超过最大值的时候，需要从0开始
						if (Conf.temp_state == 20) {
							Conf.temp_state = Conf.temp_state
									% Conf.ACTION_TO_RECORD;
						}
						// 将新接收到的数据存入到temp数组的第temp_state行
						Conf.temp[Conf.temp_state] = d;
						
						// 线程休眠指定时间后再次发送数据r，接收手环数据，休眠值从配置类中读取
						Thread.sleep(Conf.actionTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		return th;
	}
}
