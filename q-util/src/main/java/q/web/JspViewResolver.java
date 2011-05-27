/**
 * 
 */
package q.web;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 */
public class JspViewResolver implements ViewResolver {
	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.ViewResolver#view(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, q.web.Resource)
	 */
	@Override
	public ModelAndView view(ResourceContext context, Resource resource) throws ServletException, IOException {
		if (context.isEmptyView()) { // return if empty view
			return null;
		}
		if (context.isCommitted()) { // return if already committed
			return null;
		}
		String redirect = resource.getRedirectPath();
		if (redirect != null) {
			context.redirectServletPath(redirect);
			return null;
		}
		context.forward("/WEB-INF/jsp/" + resource.getName() + ".jsp");
		return null;
	}

}
