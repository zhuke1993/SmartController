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
        // TODO �Զ����ɵĹ��캯�����
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
        // TODO �Զ����ɵķ������
        byte[] buffer = new byte[10240];// ����buffer��������
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
                // �������ж�ȡ��һ���ֽ�
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

                //System.out.println("���յ�����Ϣ��" + receive);

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
     * ���������߳̽������ݣ�������ɺ���ֹ���߳�
     *
     * @param bt ����Э��ջ
     * @return ���յ�������
     */
    public static String getData(BluetoothSocket bt) {
        String s = BloothServer.receive;
        return s;

    }
}
