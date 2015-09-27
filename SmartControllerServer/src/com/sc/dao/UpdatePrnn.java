package com.sc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sc.util.DBUtil;

public class UpdatePrnn {
	public static void main(String[] args) {
		System.out.println(new UpdatePrnn().updatePrnn("1"));
	}

	/**
	 * 获得最新的神经网络训练后的参数值
	 * 
	 * @param id
	 *            用户id
	 * @return 最新的神经网络参数值，数据格式为: w1,b1,w2,b2(其中为矩阵的数据以空格互相隔开)
	 */
	public String updatePrnn(String id) {
		Connection conn = DBUtil.getConn();
		try {
			PreparedStatement pstm = conn
					.prepareStatement("select * from sc_prnn where id=? and prnn_index=(select max(prnn_index) from sc_prnn)");
			pstm.setDouble(1, Double.parseDouble(id));
			ResultSet rs = pstm.executeQuery();
			StringBuilder sb = new StringBuilder();
			rs.next();
			sb.append(rs.getString("w1") + "," + rs.getString("b1") + ","
					+ rs.getString("w2") + "," + rs.getString("b2"));
			return sb.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
