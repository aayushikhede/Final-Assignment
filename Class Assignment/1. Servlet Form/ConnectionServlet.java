package com.webAppLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/ConnectionServlet")

public class ConnectionServlet extends HttpServlet {

	String user, password, driver, url;
	Connection con;
	PreparedStatement st;
	@Override
	public void init(ServletConfig config) throws ServletException {
		user = config.getInitParameter("userName");
		password = config.getInitParameter("password");
		url = config.getInitParameter("url");
		driver = config.getInitParameter("driver");
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			System.out.println(con);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
//
//		String userName = req.getParameter("un");
//		String passWord = req.getParameter("pwd");
//		String mail = req.getParameter("mail");
//		String salary = req.getParameter("salary");
		try {
			

			String sql = "insert into registration values (?,?,?,?)";
			st = con.prepareStatement(sql);
			st.setString(1, req.getParameter("un"));
			st.setString(2, req.getParameter("pwd"));
			st.setString(3, req.getParameter("email"));
			st.setString(4, req.getParameter("country"));

			st.executeUpdate();

			st.close();
			con.close();

			pw.println("<html><body><b>Successfully Inserted" + "</b></body></html>");

			// .out.println("sHELLOOOOOOO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public void destroy() {
		try {
			st.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
