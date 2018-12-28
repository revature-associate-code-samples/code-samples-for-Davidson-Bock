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
import com.revature.pojo.Rtype;
import com.revature.pojo.Status;
import com.revature.service.StatusService;

@WebServlet("/status")
public class StatusServlet extends HttpServlet {
static StatusService statusService = new StatusService();
	
	private static Logger logger = Logger.getLogger(StatusServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String context = getServletContext().getInitParameter("AppInfo");
		logger.trace("Servlet context in status servlet" + context);
		
		List<Status> status = statusService.getAllStatus(); //get all status from "persistence"
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(status); 
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);
	}
	
	//Add Status
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			Status s = mapper.readValue(req.getInputStream(), Status.class);
			s = statusService.addStatus(s.getId(), s.getStatus());
			logger.trace("ADDED NEW STATUS " + s);
		}

}