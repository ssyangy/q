package q.web;

import org.springframework.web.servlet.ModelAndView;

public interface ViewResolver {

	ModelAndView view(ResourceContext context, Resource resource) throws Exception;

}
