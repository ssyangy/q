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
	public void view(ResourceContext context, Resource resource) throws ServletException, IOException {
		if (context.isEmptyView()) { // return if empty view
			return;
		}
		HttpServletRequest request = ((DefaultResourceContext) context).getRequest();
		HttpServletResponse response = ((DefaultResourceContext) context).getResponse();
		if (response.isCommitted()) { // return if already committed
			return;
		}
		String redirect = resource.getRedirectPath();
		if (redirect != null) {
			response.sendRedirect(request.getContextPath() + redirect);
			log.debug("redirect to path: %s", request.getContextPath() + redirect);
			return;
		}
		String jsp = "/WEB-INF/jsp/" + resource.getName() + ".jsp";
		log.debug("forward to view: %s", jsp);
		request.getRequestDispatcher(jsp).forward(request, response);
	}

}
