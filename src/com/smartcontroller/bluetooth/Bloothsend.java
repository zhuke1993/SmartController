package com.smartcontroller.bluetooth;

import java.io.IOException;
import java.io.OutputStream;

import android.bluetooth.BluetoothSocket;

/*
 * 这是用来发送数据的发送数据时先要实例化这个类，然后调用线程的start方法实现数据的发送。传递的参数
 * 为一个BluetoothSocket和一个string对象。BluetoothSocket是在前activity中声明的一个调用蓝牙
 * 时要使用的对象。
 */
public class Bloothsend extends Thread {
	OutputStream outStream=null;
	BluetoothSocket btSocket=null;
	int statue = 0;//标志变量，当为0时，说明正在发送数据；为1时，说明已经发送完成
	String message=null;
public Bloothsend(BluetoothSocket btSocket,String message) {
	// TODO 自动生成的构造函数存根
	this.btSocket=btSocket;
	this.message=message;
}
@Override
	public void run() {
		// TODO 自动生成的方法存根 
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
