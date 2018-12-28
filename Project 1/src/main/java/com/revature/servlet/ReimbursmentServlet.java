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
import com.revature.pojo.Users;
import com.revature.service.ReimbursmentService;
import com.revature.service.UserService;

@WebServlet("/reimbursments")
public class ReimbursmentServlet extends HttpServlet {
	
static ReimbursmentService reimbService = new ReimbursmentService();
	
	private static Logger logger = Logger.getLogger(ReimbursmentServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String context = getServletContext().getInitParameter("AppInfo");
		logger.trace("Servlet context in reimb servlet" + context);
		
		List<Reimbursment> reimbs = reimbService.getAllReimbursments(); //get all reimbs from "persistence"
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reimbs); 
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);
	}
	
	//Add Reimbursment
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			Reimbursment r = mapper.readValue(req.getInputStream(), Reimbursment.class);
			r = reimbService.addReimbursment(r.getAmount(), r.getSubmitted(), 
					r.getResolved(), r.getDescription(), r.getAuthor(), r.getResolver(),
					r.getStatusId(), r.getTypeId());
			logger.trace("ADDED NEW REIMBURSMENT " + r);
		}

}