package com.sc.util;

import java.sql.*;

/**
 * Created by zhuke on 2015/3/26.
 */
public class LoginDB {

	public static String login(String user_id, String user_pwd) {
		try {
			Connection conn = DBUtil.getConn();
			PreparedStatement pstm = conn
					.prepareStatement("select user_id from sc_users where user_id =? and user_pwd = ?");
			pstm.setString(1, user_id);
			pstm.setString(2, user_pwd);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return rs.getString("user_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
