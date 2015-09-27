package com.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringTokenizer;

import com.sc.util.DBUtil;

public class UpdateActions {

	public static void main(String[] args) {
		new UpdateActions().updateAction("1 1 -0.172607 0.476196 0.356689 0.464844 0.578369 0.529907 0.634155 0.266357 0.159912 0.263428 -0.239868 0.25769 0.160278 0.157349 0.164795 0.10376 -0.167847 -0.234375 -0.202515 -0.229126 0.383423 0.238647 0.14563 0.201782 0.230347 0.154297 0.301392 0.208252 0.231323 0.198853 -0.0415344 0.0722351 -0.0149841 -0.0217896 -0.0732422 -0.160065 0.00701904 -0.0988464 -0.192657 0.0664978 -0.0017395 -0.21701 -0.0169678 -0.0108032 -0.0181885 -0.040802 0.359039 0.0584106 -0.00308228 0.0335083 -0.0101929 -0.243103 0.0265808 0.0254822 0.186432 0.388641 0.424774 0.314819 0.183319 -0.176483");
	}

	/**
	 * 用户学习的动作数据更新，上传持久化到数据库中
	 * 
	 * @param s
	 *            待学习的动作数据，数据格式为:id lable ax0 ax1 ...... ax9
	 * @return 返回数据库改变的行数，如果更新失败返回-1
	 */
	public int updateAction(String s) {

		StringTokenizer st = new StringTokenizer(s, " ");
		Connection conn = DBUtil.getConn();
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstm = conn
					.prepareStatement("insert into sc_action(id,state,label,ax0, ax1, ax2, ax3, ax4, ax5, ax6, ax7, ax8, ax9, ay0, ay1, ay2, ay3, ay4, ay5, ay6, ay7, ay8, ay9, az0, az1, az2, az3, az4, az5, az6, az7, az8, az9, bx0, bx1, bx2, bx3, bx4, bx5, bx6, bx7, bx8, bx9, by0, by1, by2, by3, by4, by5, by6, by7, by8, by9, bz0, bz1, bz2, bz3, bz4, bz5, bz6, bz7, bz8, bz9) values(?, 0, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?,?)");

			for (int i = 0; i < 62; i++) {
				pstm.setDouble(i + 1,
						Double.parseDouble(st.nextToken().toString()));
			}
			int r = pstm.executeUpdate();
			conn.commit();
			conn.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return 0;
	}
}
