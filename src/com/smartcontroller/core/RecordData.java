package com.smartcontroller.core;

import com.smartcontroller.bluetooth.BluetoothCenter;
import com.smartcontroller.util.Conf;
import com.smartcontroller.util.DataFormat;

/**
 * ���ݼ�¼�̣߳����Ͻ��մ��ֻ��������͹���������
 * 
 * @author Administrator
 * 
 */
public class RecordData {
	/**
	 * ���ݼ�¼�̣߳����Ͻ��մ��ֻ��������͹���������
	 * 
	 * @return ���ݼ�¼�߳�
	 */
	public static Thread recordData() {
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				BluetoothCenter.btserver.start();
				while (true) {
					try {
						String acc = null;
						// ������Ҫ״̬����Ҫͨ��������ʱ��������������r���õ��������ػ����ĸ�״̬��״̬����
						BluetoothCenter.send("r");
						//System.out.println("���ֻ�������һ������ֵr");
						// �����������ػص�һ��״̬��Ϣ������6��doubleֵ��string���м��Կո������
						acc = BluetoothCenter.accept();

						System.out.println("���ֻ��õ������ʱ��״ֵ̬:" + acc);
						
						// �����ݽ��й�һ����������������ά������
						double[] d = DataFormat.format(acc);

						// Conf.temp_state����
						Conf.temp_state++;
						// temp����Ϊһ��ѭ�����У���temp_state��ֵ�������ֵ��ʱ����Ҫ��0��ʼ
						if (Conf.temp_state == 20) {
							Conf.temp_state = Conf.temp_state
									% Conf.ACTION_TO_RECORD;
						}
						// ���½��յ������ݴ��뵽temp����ĵ�temp_state��
						Conf.temp[Conf.temp_state] = d;
						
						// �߳�����ָ��ʱ����ٴη�������r�������ֻ����ݣ�����ֵ���������ж�ȡ
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
