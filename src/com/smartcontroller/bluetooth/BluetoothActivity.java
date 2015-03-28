/*
 * �����Ĳ�������ʵ����һ��BluetoothAdapter������Ȼ������Ѿ��еķ���ʵ�����������������ã�
 * BluetoothSocket�İ󶨣����ݵķ��ͺͽ��ն���ͨ��BluetoothSocketʵ�ֵġ��������Ѿ���
 * ���ֵ��豸������ʾ��һ��listview���档������ѡ��������ø��豸�����ǽ�׳���еĵط�������������
 * ͨ����û������ġ�
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
		
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth);
		mlist=(ListView)findViewById(R.id.listView1);
		but_back=(Button)findViewById(R.id.but_back);
		but_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				Intent intent=new Intent();
				intent.setClass(BluetoothActivity.this, Main.class);
				startActivity(intent);
				//Learn.flag =1;
			}
		});
		//mArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
		adapter=new SimpleAdapter(this, list,R.layout.list_item, new String[]{"name","address"},
				new int[]{R.id.textname,R.id.textaddress});
		//��ȡ����adapter����
	    
	    /*
	     * ��������
	     */
		mlist.setAdapter(adapter);
                
	    mBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
				//�ж�adapter�����Ƿ�Ϊ�գ���Ϊ�գ���˵��û���ҵ���������
				if(mBluetoothAdapter!=null){
					Toast.makeText(getApplicationContext(), "���������豸����", Toast.LENGTH_SHORT).show();
					//isenable�жϵ�ǰ�����Ƿ����
					if(!mBluetoothAdapter.isEnabled()){
						Toast.makeText(getApplicationContext(), "�����豸�����ã���������豸����������������", Toast.LENGTH_SHORT).show();
					}
				
				mBluetoothAdapter.startDiscovery();//��ʼɨ��
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
	                //�ر�ɨ��   
	                //mBluetoothAdapter.cancelDiscovery();  
	                
	            }  
				
                mlist.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO �Զ����ɵķ������
						Map<String, Object> map=(HashMap<String, Object>)mlist.getItemAtPosition(position);
						String addr=(String) map.get("address");
						address=addr;
						recon();
						BloothServer bs=new BloothServer(btSocket);
						bs.start();
						
						//��btSocket����ע�뵽bluetoothCenter��ȥ
						BluetoothCenter.btSocket = btSocket;
						
					}
				});
                
                  
	}
	public void recon(){
	  	 BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);  
	  	  
	       try {  
	           btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);  
	       } catch (IOException e) {
	      	 Toast.makeText(getApplicationContext(), "Socketʧ��", Toast.LENGTH_SHORT).show();

	       }  
	       mBluetoothAdapter.cancelDiscovery();  
	       try {  

	           btSocket.connect();
	          
	           Toast.makeText(getApplicationContext(), "�������ӳɹ�", Toast.LENGTH_SHORT).show();

	       } catch (IOException e) {  
	      	 Toast.makeText(getApplicationContext(), "��������ʧ��", Toast.LENGTH_SHORT).show();
	      	// alert.show();
	      	 try {  
	               btSocket.close();  
	               Toast.makeText(getApplicationContext(), "Socket�ر�", Toast.LENGTH_SHORT).show();
	           } catch (IOException e2) {  
	          	 Toast.makeText(getApplicationContext(), "Socket�ر�ʧ��", Toast.LENGTH_SHORT).show();
	              
	          }  
	       }
	 }

}
