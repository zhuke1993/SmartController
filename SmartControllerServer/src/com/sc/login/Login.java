package com.sc.login;

import java.sql.*;

import com.sc.util.DBUtil;

/**
 * Created by zhuke on 2015/3/26.
 */
public class Login {

	public static int login(String user_id, String user_pwd) {
		try {
			Connection conn = DBUtil.getConn();
			PreparedStatement pstm = conn
					.prepareStatement("select user_id from sc_users where user_id =? and user_pwd = ?");
			pstm.setString(1, user_id);
			pstm.setString(2, user_pwd);
			System.out.println("a connection reached:"+user_id+":"+user_pwd);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
