package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


public class LoadViewsServlet extends HttpServlet  {
	
private static Logger log  = Logger.getLogger(LoadViewsServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String resourcePath = "partials/" + process(req, resp) +".html";
		req.getRequestDispatcher(resourcePath).forward(req, resp);
	}
	
	static String process(HttpServletRequest req, HttpServletResponse resp) {
		log.info("LOAD VIEW REQUEST SENT TO: " + req.getRequestURI());
		System.out.println(req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/project1/employee-home.view":
			return "employee-home";
		case "/project1/type.view":
			return "types";
			
		case "/project1/reimbursment.view":
			return "reimbursments";
		case "/project1/status.view":
			return "status";
		case "/project1/userrole.view":
			return "userroles";
		case "/project1/users.view":
			return "users";
		case "/project1/login.view":
			return "login";
		case "/project1/signup.view":
			return "signup";
		case "/project1/manager-home.view":
			return "manager-home";
			
		case "/project1/requestApproval.view":
			return "requestApproval";
		case "/project1/request.view":
			return "request";
		case "/project1/statuslist.view":
			return "statuslist";
		}
		
		
		return null;
	}

}