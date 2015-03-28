/*
 * 蓝牙的操作是先实例化一个BluetoothAdapter容器，然后调用已经有的方法实现蓝牙的搜索，调用，
 * BluetoothSocket的绑定，数据的发送和接收都是通过BluetoothSocket实现的。这里我已经将
 * 发现的设备存在显示在一个listview里面。根据所选择的设别调用该设备。但是健壮性有的地方还不够。不过
 * 通信是没有问题的。
 */
package com.smartcontroller.bluetooth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.smartcontroller.R;
import com.smartcontroller.core.Learn;
import com.smartcontroller.core.Main;
import com.smartcontroller.util.FileUtil;
import com.smartcontroller.wifi.WifiCenter;

public class BluetoothActivity extends Activity {
	Button but_back=null;
	ListView mlist; 
	SimpleAdapter adapter=null;
	List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
	private BluetoothAdapter mBluetoothAdapter = null;  
	private BluetoothSocket btSocket = null;  
	private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private String address;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth);
		mlist=(ListView)findViewById(R.id.listView1);
		but_back=(Button)findViewById(R.id.but_back);
		but_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				Intent intent=new Intent();
				intent.setClass(BluetoothActivity.this, Main.class);
				startActivity(intent);
				//Learn.flag =1;
			}
		});
		//mArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
		adapter=new SimpleAdapter(this, list,R.layout.list_item, new String[]{"name","address"},
				new int[]{R.id.textname,R.id.textaddress});
		//获取蓝牙adapter对象
	    
	    /*
	     * 蓝牙操作
	     */
		mlist.setAdapter(adapter);
                
	    mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
				//判断adapter对象是否为空，若为空，则说明没有找到蓝牙对象。
				if(mBluetoothAdapter!=null){
					Toast.makeText(getApplicationContext(), "本机蓝牙设备可用", Toast.LENGTH_SHORT).show();
					//isenable判断当前蓝牙是否可用
					if(!mBluetoothAdapter.isEnabled()){
						Toast.makeText(getApplicationContext(), "蓝牙设备不可用，请打开蓝牙设备并重新启动本程序", Toast.LENGTH_SHORT).show();
					}
				
				mBluetoothAdapter.startDiscovery();//开始扫描
				final BroadcastReceiver mReceiver = new BroadcastReceiver()   
                {  
                    @Override  
                    public void onReceive(Context context, Intent intent)   
                    {    
                        String action = intent.getAction();   
                        if (BluetoothDevice.ACTION_FOUND.equals(action))   
                        {    
                            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);  
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put("name", device.getName()+"   ");
                            map.put("address", device.getAddress());                       
                            list.add(map);
                            adapter.notifyDataSetChanged();
                   
                        }   
                    }  
                };  
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);  
                registerReceiver(mReceiver, filter);
                
				}
				else  
	            {  
	                //关闭扫描   
	                //mBluetoothAdapter.cancelDiscovery();  
	                
	            }  
				
                mlist.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO 自动生成的方法存根
						Map<String, Object> map=(HashMap<String, Object>)mlist.getItemAtPosition(position);
						String addr=(String) map.get("address");
						address=addr;
						recon();
						BloothServer bs=new BloothServer(btSocket);
						bs.start();
						
						//将btSocket属性注入到bluetoothCenter中去
						BluetoothCenter.btSocket = btSocket;
						
					}
				});
                
                  
	}
	public void recon(){
	  	 BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);  
	  	  
	       try {  
	           btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);  
	       } catch (IOException e) {
	      	 Toast.makeText(getApplicationContext(), "Socket失败", Toast.LENGTH_SHORT).show();

	       }  
	       mBluetoothAdapter.cancelDiscovery();  
	       try {  

	           btSocket.connect();
	          
	           Toast.makeText(getApplicationContext(), "蓝牙连接成功", Toast.LENGTH_SHORT).show();

	       } catch (IOException e) {  
	      	 Toast.makeText(getApplicationContext(), "蓝牙连接失败", Toast.LENGTH_SHORT).show();
	      	// alert.show();
	      	 try {  
	               btSocket.close();  
	               Toast.makeText(getApplicationContext(), "Socket关闭", Toast.LENGTH_SHORT).show();
	           } catch (IOException e2) {  
	          	 Toast.makeText(getApplicationContext(), "Socket关闭失败", Toast.LENGTH_SHORT).show();
	              
	          }  
	       }
	 }

}
