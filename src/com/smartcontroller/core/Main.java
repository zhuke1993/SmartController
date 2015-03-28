package com.smartcontroller.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import libsvm.svm;
import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.smartcontroller.R;
import com.smartcontroller.bluetooth.BloothServer;
import com.smartcontroller.bluetooth.BluetoothActivity;
import com.smartcontroller.bluetooth.BluetoothCenter;
import com.smartcontroller.util.Conf;
import com.smartcontroller.util.FileUtil;
import com.smartcontroller.wifi.UDPServer;

public class Main extends Activity {
	BluetoothSocket btsocket=null;
	private Button but_searchbloothdevice=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		UDPServer us=new UDPServer();
		us.start();
		//���ȶ�Ӧ������Ҫ�ĸ����ļ����г�ʼ������
		FileUtil.iniFile();
		System.out.println("�ļ���ʼ���ɹ�");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 //���������豸�����������豸
		but_searchbloothdevice=(Button)findViewById(R.id.but_searchbloothdevice);
		but_searchbloothdevice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent();
				intent.setClass(getApplication(), BluetoothActivity.class);
				startActivity(intent);//������ѡ������ҳ��
			}
		});
		
//======================================================================//
		if(BluetoothCenter.btSocket == null){
			Intent intent=new Intent();
			intent.setClass(getApplication(), BluetoothActivity.class);
			startActivity(intent);//������ѡ������ҳ��
		}else{
			BluetoothCenter.btserver = new BloothServer(BluetoothCenter.btSocket);
			// ��model����action�ļ���������
			try {
				Conf.MODEL = svm.svm_load_model(Conf.model_f);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//���ݼ�¼�߳�
			Thread th_record = RecordData.recordData();
			//����Ԥ���߳�
			Thread th_predict = Predict.predict();
			//ѧϰ�߳�
			Thread th_learn = Learn.learn();
			
			th_record.start();
			th_learn.start();
			th_predict.start();
		}
		
		
//=====================================================================//
		
		GridView grid;// ���üҵ�ͼƬ������
		Switch run_switcher;// ѡ���Ƿ��Զ�Ԥ��

		int[] imageIds = new int[] { R.drawable.furniture_light1,
				R.drawable.furniture_lamp, R.drawable.furniture_cable,
				R.drawable.furniture_light2, R.drawable.furniture_tv };

		// ����switch������ϵͳ�Ƿ����С�
		OnCheckedChangeListener runListener = new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton Button,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// ��ʼ�Զ�Ԥ��
					// toast��������ʾ�û���ʼ�Զ�Ԥ��
					Toast.makeText(getApplicationContext(), "��ʼ�Զ�Ԥ��",
							Toast.LENGTH_SHORT).show();
					// �˴�Ӧ�ÿ�ʼ�Զ�Ԥ������

				} else {
					Toast.makeText(getApplicationContext(), "ֹͣ�Զ�Ԥ��",
							Toast.LENGTH_SHORT).show();
					// �˴�Ӧ�����ֹͣ�Զ�Ԥ������

				}
			}
		};
		run_switcher = (Switch) findViewById(R.id.switch_run);
		run_switcher.setOnCheckedChangeListener(runListener);

		// ����һ��List����List�����Ԫ����Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imageIds.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", imageIds[i]);
			listItems.add(listItem);
		}
		// ����һ��SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,
				listItems
				// ʹ��/layout/cell.xml�ļ���Ϊ���沼��
				, R.layout.cell, new String[] { "image" },
				new int[] { R.id.furniture_img });
		grid = (GridView) findViewById(R.id.furniture_grid);
		// ΪGridView����Adapter
		grid.setAdapter(simpleAdapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
