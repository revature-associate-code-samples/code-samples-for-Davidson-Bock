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

@WebServlet("/signupinfo")
public class CreateAccountServlet extends HttpServlet {

	static UserService userService = new UserService();
	static UserDao userDao = new UserDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Testing in doGet");
		//functionality to go back to login.html
		//HttpSession session = req.getSession();
		//Users user = (Users) session.getAttribute("user");
		req.getRequestDispatcher("signupinfo.html").forward(req, resp);



		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//llogin functionality here:
		//HttpSession session = req.getSession();
		//Users user = (Users) session.getAttribute("user");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String r = req.getParameter("role");
		int role = Integer.parseInt(r);
		
		Users  user = userService.getAccountInfo(username, password, firstName, lastName, email, role);
		System.out.println("Test: " +  user);
		req.getRequestDispatcher("signupinfo.html").forward(req, resp);
		System.out.println("Test: " +  user);
		

		
	}
		

}