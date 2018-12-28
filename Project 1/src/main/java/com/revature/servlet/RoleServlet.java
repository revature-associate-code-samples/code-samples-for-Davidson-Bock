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
import com.revature.pojo.Reimbursment;
import com.revature.pojo.UserRole;
import com.revature.service.RoleService;

@WebServlet("/roles")
public class RoleServlet extends  HttpServlet {
static RoleService roleService = new RoleService();
	
	private static Logger logger = Logger.getLogger(RoleServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String context = getServletContext().getInitParameter("AppInfo");
		logger.trace("Servlet context in role servlet" + context);
		
		List<UserRole> roles = roleService.getAllRoles(); //get all roles from "persistence"
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(roles); 
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);
	}
	
	//Add Role
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			UserRole r = mapper.readValue(req.getInputStream(), UserRole.class);
			r = roleService.addRole(r.getId(), r.getRole());
			logger.trace("ADDED NEW Role " + r);
		}

}