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
import com.revature.pojo.Rtype;
import com.revature.service.RtypeService;

@WebServlet("/types")
public class RtypeServlet extends HttpServlet {
static RtypeService typeService = new RtypeService();
	
	private static Logger logger = Logger.getLogger(RtypeServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String context = getServletContext().getInitParameter("AppInfo");
		logger.trace("Servlet context in type servlet" + context);
		
		List<Rtype> types = typeService.getAllRtypes(); //get all types from "persistence"
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(types); 
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);
	}
	
	//Add Type
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			Rtype r = mapper.readValue(req.getInputStream(), Rtype.class);
			r = typeService.addType(r.getId(), r.getType());
			logger.trace("ADDED NEW TYPE " + r);
		}

}