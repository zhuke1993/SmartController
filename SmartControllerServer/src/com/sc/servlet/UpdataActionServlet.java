package com.sc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sc.dao.UpdateActions;

public class UpdataActionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String s = req.getParameter("action");
		if (s != null) {
			s.replace("%20", " ");
			int r =new  UpdateActions().updateAction(s);
			System.out.println(r);
			PrintWriter writer = resp.getWriter();
			writer.write(new Integer(r).toString());
			writer.flush();
			writer.close();
		}

	}
}
