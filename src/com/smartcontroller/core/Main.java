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
		//首先对应用所需要的各类文件进行初始化创建
		FileUtil.iniFile();
		System.out.println("文件初始化成功");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 //搜索蓝牙设备及连接蓝牙设备
		but_searchbloothdevice=(Button)findViewById(R.id.but_searchbloothdevice);
		but_searchbloothdevice.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				intent.setClass(getApplication(), BluetoothActivity.class);
				startActivity(intent);//打开蓝牙选择连接页面
			}
		});
		
//======================================================================//
		if(BluetoothCenter.btSocket == null){
			Intent intent=new Intent();
			intent.setClass(getApplication(), BluetoothActivity.class);
			startActivity(intent);//打开蓝牙选择连接页面
		}else{
			BluetoothCenter.btserver = new BloothServer(BluetoothCenter.btSocket);
			// 对model根据action文件进行载入
			try {
				Conf.MODEL = svm.svm_load_model(Conf.model_f);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//数据记录线程
			Thread th_record = RecordData.recordData();
			//动作预测线程
			Thread th_predict = Predict.predict();
			//学习线程
			Thread th_learn = Learn.learn();
			
			th_record.start();
			th_learn.start();
			th_predict.start();
		}
		
		
//=====================================================================//
		
		GridView grid;// 放置家电图片的网格
		Switch run_switcher;// 选择是否自动预测

		int[] imageIds = new int[] { R.drawable.furniture_light1,
				R.drawable.furniture_lamp, R.drawable.furniture_cable,
				R.drawable.furniture_light2, R.drawable.furniture_tv };

		// 监听switch，控制系统是否运行。
		OnCheckedChangeListener runListener = new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton Button,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// 开始自动预测
					// toast交互，提示用户开始自动预测
					Toast.makeText(getApplicationContext(), "开始自动预测",
							Toast.LENGTH_SHORT).show();
					// 此处应该开始自动预测数据

				} else {
					Toast.makeText(getApplicationContext(), "停止自动预测",
							Toast.LENGTH_SHORT).show();
					// 此处应该添加停止自动预测数据

				}
			}
		};
		run_switcher = (Switch) findViewById(R.id.switch_run);
		run_switcher.setOnCheckedChangeListener(runListener);

		// 创建一个List对象，List对象的元素是Map
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < imageIds.length; i++) {
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", imageIds[i]);
			listItems.add(listItem);
		}
		// 创建一个SimpleAdapter
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,
				listItems
				// 使用/layout/cell.xml文件作为界面布局
				, R.layout.cell, new String[] { "image" },
				new int[] { R.id.furniture_img });
		grid = (GridView) findViewById(R.id.furniture_grid);
		// 为GridView设置Adapter
		grid.setAdapter(simpleAdapter);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
