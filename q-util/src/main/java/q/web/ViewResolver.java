package q.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewResolver {

	void view(HttpServletRequest request, HttpServletResponse response, Resource resource) throws ServletException, IOException;

}
