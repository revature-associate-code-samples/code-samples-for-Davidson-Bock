package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojo.Users;
import com.revature.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	static UserService userService = new UserService();
	static LoadViewsServlet home = new LoadViewsServlet();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//functionality to go back to login.html
		userService.getAllUsers();
		req.getRequestDispatcher("login.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//llogin functionality here:
		String username = req.getParameter("username");
		System.out.println(username);
		String password = req.getParameter("password");
		String role = req.getParameter("userRoleId");
		System.out.println(role);
		
		//consult user servicce to obtain User with this info
		Users user = userService.validiateUser(username, password);
		int roleId = user.getUserRoleId();
		System.out.println(user.getUserRoleId());
		System.out.println(user);

		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		String text = "";
		if(user == null) {
			req.getRequestDispatcher("login.html").forward(req, resp);
			writer.write("<h1>Invalid Username or Password!</h1>");
			//add a button to go back to login screen?
		}
		else if(roleId == 1){

			HttpSession session = req.getSession();

			session.setAttribute("user", user);

			String p = home.process(req, resp);
			resp.sendRedirect("partials/employee-home.html");
	
		}
		else {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect("partials/manager-home.html");
			
		}
		

	}

}
