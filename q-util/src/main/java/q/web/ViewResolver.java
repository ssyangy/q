package q.web;

import java.io.IOException;

import javax.servlet.ServletException;

public interface ViewResolver {

	void view(ResourceContext context, Resource resource) throws ServletException, IOException;

}
