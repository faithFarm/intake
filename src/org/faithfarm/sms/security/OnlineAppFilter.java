package org.faithfarm.sms.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.faithfarm.sms.domain.Farm;
import org.faithfarm.sms.util.HtmlDropDownBuilder;

public class OnlineAppFilter implements Filter {

	private final static Logger LOGGER = Logger.getLogger(OnlineAppFilter.class.getName());
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		LOGGER.setLevel(Level.SEVERE);
		
		HtmlDropDownBuilder html = new HtmlDropDownBuilder();
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getServletPath();
		String contextPath = request.getContextPath();

		//Check if drops downs exist, if not load them
		HttpSession session = request.getSession(true);
		if (session.getAttribute("ddl_farm")==null) 
			req.getRequestDispatcher("/OnlineApp.do").forward(req, res);
		else
			req.getRequestDispatcher(url).forward(req, res);
			//html.refresh(session);
	}

	public void init(FilterConfig config) throws ServletException {

	}

	public void destroy() {
	}


}
