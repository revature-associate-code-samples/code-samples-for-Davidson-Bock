package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
private static Logger logger = Logger.getLogger(LogoutServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String toTrace = "Logging out session with attributes: /n";
		Enumeration<String> atts = session.getAttributeNames();
		while(atts.hasMoreElements()) {
			toTrace += atts.nextElement() + "\n";
		}
		logger.trace(toTrace);
		if(session != null) {
			session.invalidate();
			logger.trace("INVALIDATING SESSION" + session.getId());
			toTrace = "";

		}
		PrintWriter writer = resp.getWriter();
		String out = "You are successfully logged out!";
		out += "<a href= \"login\"> Back to Log In page! </a>";
		resp.setContentType("text/html");
		writer.write(out);
	}

}