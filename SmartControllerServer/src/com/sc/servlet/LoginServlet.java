package com.sc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sc.util.LoginDB;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String user_id = req.getParameter("phone");
		String user_pwd = req.getParameter("pwd");
		if (user_id != null && user_pwd != null) {
			String r = LoginDB.login(user_id, user_pwd);
			PrintWriter writer = resp.getWriter();
			writer.print(r);
			writer.flush();
			writer.close();
		}

	}
}
