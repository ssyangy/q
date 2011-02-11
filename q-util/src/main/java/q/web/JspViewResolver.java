/**
 * 
 */
package q.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import q.log.Logger;

/**
 * @author seanlinwang
 * @date Jan 18, 2011 
 */
public class JspViewResolver implements ViewResolver {
	private final static Logger log = Logger.getLogger();
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.ViewResolver#view(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, q.web.Resource)
	 */
	@Override
	public void view(HttpServletRequest request, HttpServletResponse response, String viewName) throws ServletException, IOException {
		String jsp = "/WEB-INF/jsp/" + viewName + ".jsp";
		log.debug("find jsp view: %s", jsp);
		request.getRequestDispatcher(jsp).forward(request, response);
	}

}
