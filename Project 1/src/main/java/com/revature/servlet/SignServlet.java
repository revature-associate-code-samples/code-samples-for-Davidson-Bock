package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDao;
import com.revature.pojo.Users;
import com.revature.service.UserService;

@WebServlet("/signup")
public class SignServlet extends HttpServlet {
	static UserService userService = new UserService();
	static UserDao userDao = new UserDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//functionality to go back to login.html
		req.getRequestDispatcher("signup.html").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//llogin functionality here:
		
		String username = req.getParameter("username");
		System.out.println("1st: " +username);

		
		//consult user servicce to obtain User with this info
		//Users user = userService.validiateUser(username, password);
		Users user = userService.validiateNewUser(username);
		System.out.println(user);
		
		
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		String text = "";
		if(user != null) {
			
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect("signupinfo");
		}
		else {
			
		}
		
		
	}

	
	

}
