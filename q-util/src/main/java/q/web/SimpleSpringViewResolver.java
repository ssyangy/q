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
public class SimpleSpringViewResolver implements ViewResolver {
	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.ViewResolver#view(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, q.web.Resource)
	 */
	@Override
	public ModelAndView view(ResourceContext context, Resource resource) throws ServletException, IOException {
		return new ModelAndView(resource.getName());
	}

}
