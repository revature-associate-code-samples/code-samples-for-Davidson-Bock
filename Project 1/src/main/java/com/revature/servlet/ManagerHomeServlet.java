package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.pojo.Users;

@WebServlet("/manager-home")
public class ManagerHomeServlet extends HttpServlet  {
	
private static Logger logger = Logger.getLogger(HomeServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//check if user session if available. if so, return home view, if not redirect
		HttpSession session = req.getSession();
		logger.trace("IN HOMESERVLET.SESSION: " + session.getId());
		
		Users user = (Users) session.getAttribute("user");
	
	}

}
