package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Users;
import com.revature.service.UserService;

@WebServlet("/users")
public class UserServlet extends HttpServlet{
	
	static UserService userService = new UserService();
	
	private static Logger logger = Logger.getLogger(UserServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String context = getServletContext().getInitParameter("AppInfo");
		logger.trace("Servlet contexrt in user sefrvlet" + context);
		
		List<Users> users = userService.getAllUsers(); //get all users from "persistence"
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(users); 
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);
	}
	
	//Add User
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			
			ObjectMapper mapper = new ObjectMapper();
			Users u = mapper.readValue(req.getInputStream(), Users.class);
			u = userService.addUser(u.getId(),u.getUsername(), u.getPassword(), u.getFirstName()
					, u.getLastName(), u.getEmail(), u.getUserRoleId());
			logger.trace("ADDED NEW USER " + u);
		}

}