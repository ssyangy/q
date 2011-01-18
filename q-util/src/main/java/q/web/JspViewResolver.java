/**
 * 
 */
package q.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 * 
 */
public class JspViewResolver implements ViewResolver {

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.ViewResolver#view(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, q.web.Resource)
	 */
	@Override
	public void view(HttpServletRequest request, HttpServletResponse response, Resource resource) throws ServletException, IOException {
		String jsp = "/" + resource.getName() + ".jsp";
		request.getRequestDispatcher(jsp).forward(request, response);
	}

}
