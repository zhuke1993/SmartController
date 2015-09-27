package com.sc.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sc.dao.UpdateActions;

public class DBUtil {
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 7; i++) {
			BufferedReader bufr = new BufferedReader(new InputStreamReader(
					new FileInputStream(
							"D:/Users/Administrator/Desktop/action_" + i
									+ ".txt")));
			String temp = null;
			while ((temp = bufr.readLine()) != null) {
				temp="18883283704 "+i+" "+temp;
				new UpdateActions().updateAction(temp);
				temp = null;
			}
		}

	}

	public static Connection getConn() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://zk929184318.gotoftp3.com:3306/zk929184318",
					"zk929184318", "929184318");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
