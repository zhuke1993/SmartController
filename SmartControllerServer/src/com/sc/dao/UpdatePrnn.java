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
	 * ������µ�������ѵ����Ĳ���ֵ
	 * 
	 * @param id
	 *            �û�id
	 * @return ���µ����������ֵ�����ݸ�ʽΪ: w1,b1,w2,b2(����Ϊ����������Կո������)
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
