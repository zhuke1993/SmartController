package com.smartcontroller.bluetooth;

import java.io.IOException;
import java.io.InputStream;

import android.bluetooth.BluetoothSocket;

import com.smartcontroller.util.StringUtil;

public class BloothServer extends Thread {
    public static String receive = null;
    BluetoothSocket btSocket = null;
    private String buf = "a";

    public BloothServer(BluetoothSocket bt) {
        this.btSocket = bt;
        // TODO 自动生成的构造函数存根
    }

    public String getReceive() {
        return receive;
    }

    public BluetoothSocket getBtSocket() {
        return btSocket;
    }

    public void setBtSocket(BluetoothSocket btSocket) {
        this.btSocket = btSocket;
    }

    @Override
    public void run() {
        // TODO 自动生成的方法存根
        byte[] buffer = new byte[10240];// 开辟buffer接收数组
        byte[] buf_data = null;
        int bytes;
        InputStream mmInStream = null;

        try {
            mmInStream = btSocket.getInputStream();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        while (true) {
            try {
                // 数据流中读取下一个字节
                if ((bytes = mmInStream.read(buffer)) > 0) {
                    buf_data = new byte[bytes];
                    for (int i = 0; i < bytes; i++) {
                        buf_data[i] = buffer[i];
                    }
                }
                String s = new String(buf_data);


                buf = buf + s;
                //System.out.println("part is :"+receive);
                if (buf.charAt(buf.length() - 1) == 'b') {
                    String sbuf = buf;
                    //receive=buf;
                    buf = "";
                    StringBuffer st = new StringBuffer(sbuf);
                    st.deleteCharAt(0);
                    st.deleteCharAt(st.length() - 1);
                    String ss = st.toString();
                    if (StringUtil.isa2z(ss)) {
                        BloothServer.receive = ss;
                        //System.out.println("all is:"+receive);
                    }
                }

                //System.out.println("接收到的信息是" + receive);

            } catch (IOException e) {
                try {
                    mmInStream.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                break;
            }
        }
        super.run();
    }

    /**
     * 启动蓝牙线程接收数据，接收完成后终止该线程
     *
     * @param bt 蓝牙协议栈
     * @return 接收到的数据
     */
    public static String getData(BluetoothSocket bt) {
        String s = BloothServer.receive;
        return s;

    }
}
