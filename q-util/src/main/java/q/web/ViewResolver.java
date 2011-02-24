package q.web;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.web.servlet.ModelAndView;

public interface ViewResolver {

	ModelAndView view(ResourceContext context, Resource resource) throws ServletException, IOException;

}
