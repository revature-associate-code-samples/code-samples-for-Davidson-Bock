package com.revature.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.service.ReimbursmentService;

@WebServlet("/request")
public class RequestServlet extends HttpServlet  {
	
	static ReimbursmentService reimb = new ReimbursmentService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//functionality to go back to login.html

		req.getRequestDispatcher("partials/request.html").forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String a = req.getParameter("amount");
		
		int amount = Integer.parseInt(a);
		String submitted = req.getParameter("submitted");
		String description = req.getParameter("description");
		String t = req.getParameter("role");
		int type = Integer.parseInt(t);
		

		reimb.addReimbursment(amount, submitted, "r", description, 0, 0, 1, type);
		//resp.sendRedirect("partials/employee-home.html");
		//req.getRequestDispatcher("partials/employee-home.html").forward(req, resp);
		
		
		
	}

}