package com.smartcontroller.bluetooth;

import java.io.IOException;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;

/*
 * ���������������ݵķ�������ʱ��Ҫʵ��������࣬Ȼ������̵߳�start����ʵ�����ݵķ��͡����ݵĲ���
 * Ϊһ��BluetoothSocket��һ��string����BluetoothSocket����ǰactivity��������һ����������
 * ʱҪʹ�õĶ���
 */
public class Bloothsend extends Thread {
	OutputStream outStream=null;
	BluetoothSocket btSocket=null;
	int statue = 0;//��־��������Ϊ0ʱ��˵�����ڷ������ݣ�Ϊ1ʱ��˵���Ѿ��������
	String message=null;
public Bloothsend(BluetoothSocket btSocket,String message) {
	// TODO �Զ����ɵĹ��캯�����
	this.btSocket=btSocket;
	this.message=message;
}
@Override
	public void run() {
		// TODO �Զ����ɵķ������ 
    byte [] msgBuffer;  
    try {  
        outStream = btSocket.getOutputStream(); 
        //System.out.println("Output Stream creation");
    } catch (IOException e) {  
    	//System.out.println("Output Stream creation failed");
    }  
    msgBuffer =message.getBytes(); 
    //System.out.println(msgBuffer);
    try{  
        outStream.write(msgBuffer); 
        //System.out.println("Output....."+message);
    } catch (IOException e) {  
    	//System.out.println(" Exception during write");
    }         
		super.run();
		//Bloothsend.destroyTh(this);
	}
	
	public static void destroyTh(Thread th){
		if(th != null){
			th.interrupt();
		}
	}
}
