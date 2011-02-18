/**
 * 
 */
package q.web.testrouter;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.cactus.ServletTestCase;
import org.apache.cactus.WebRequest;
import org.apache.cactus.WebResponse;
import org.apache.cactus.extension.jetty.Jetty5xTestSetup;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import q.web.DefaultResourceContext;
import q.web.ResourceRouter;
import q.web.ViewResolver;

/**
 * Tests for resource routing.
 * 
 * @author seanlinwang
 * @date Jan 18, 2011
 * 
 */
public class ResourceRouterTest extends ServletTestCase {
	public static Test suite() {
		System.setProperty("cactus.contextURL", "http://localhost:8080/cactustest");
		TestSuite suite = new TestSuite();
		suite.addTestSuite(ResourceRouterTest.class);
		Jetty5xTestSetup setup = new Jetty5xTestSetup(suite);
		setup.setResourceDir(new File("src/test/resources/q/web/testrouter"));
		return setup;
	}

	private WebApplicationContext applicationContext;

	@Override
	protected void setUp() throws Exception {
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		getRouter().setViewResolver(new ViewResolver() {

			@Override
			public void view(HttpServletRequest request, HttpServletResponse response, String viewName) throws ServletException, IOException {
				@SuppressWarnings("unchecked")
				Enumeration<String> attributeNames = request.getAttributeNames();
				for (; attributeNames.hasMoreElements();) {
					String key = (String) attributeNames.nextElement();
					response.getWriter().write((String) request.getAttribute(key));
				}
				response.getWriter().flush();
				response.getWriter().close();
			}
		});
	}

	private ResourceRouter getRouter() {
		return ((ResourceRouter) applicationContext.getBean("router"));
	}
	// ------------------ route to get index ----------------------------
	public void beginRouteGetIndex(WebRequest theRequest) {
		theRequest.setURL("serverName.com", "/contextPath", "/a", null, null);
	}

	public void testRouteGetIndex() throws ServletException, IOException {
		getRouter().handleRequest(request, response);
	}

	public void endRouteGetIndex(WebResponse response) {
		assertEquals(200, response.getStatusCode());
		assertEquals("index", response.getText());
		//assertEquals("testcontext", request.getAttribute("contextPath"));
		//assertEquals("http://testurl", request.getAttribute("urlPrefix"));
	}
	
	// ------------------ route to get new ----------------------------
	public void beginRouteGetNew(WebRequest theRequest) {
		theRequest.setURL("serverName.com", "/contextPath", "/a/new", null, null);
	}

	public void testRouteGetNew() throws ServletException, IOException {
		getRouter().handleRequest(request, response);
	}

	public void endRouteGetNew(WebResponse response) {
		assertEquals(200, response.getStatusCode());
		assertEquals("new", response.getText());
	}

	// ------------------ route to get edit ----------------------------
	public void beginRouteGetEdit(WebRequest theRequest) {
		theRequest.setURL("serverName.com", "/contextPath", "/a/111/edit", null, null);
	}

	public void testRouteGetEdit() throws ServletException, IOException {
		getRouter().handleRequest(request, response);
	}

	public void endRouteGetEdit(WebResponse response) {
		assertEquals(200, response.getStatusCode());
		assertEquals("edit", response.getText());
	}
	
	// ------------------ route to get ----------------------------
	public void beginRouteGet(WebRequest theRequest) {
		theRequest.setURL("serverName.com", "/contextPath", "/a/123456", null, null);
	}

	public void testRouteGet() throws ServletException, IOException {
		getRouter().handleRequest(request, response);
		assertEquals("123456", new DefaultResourceContext(request, "/a/123456").getResourceLastId());
	}

	public void endRouteGet(WebResponse response) {
		assertEquals(200, response.getStatusCode());
		assertEquals("123456", response.getText());
	}

	// ------------------ route to post ----------------------------
	public void beginRoutePost(WebRequest theRequest) {
		theRequest.setURL("serverName.com", "/contextPath", "/a", null, null);
		theRequest.addParameter("email", "xalinx@gmail.com", WebRequest.POST_METHOD);
	}

	public void testRoutePost() throws ServletException, IOException {
		getRouter().handleRequest(request, response);
	}

	public void endRoutePost(WebResponse response) {
		assertEquals(200, response.getStatusCode());
		assertEquals("xalinx@gmail.com", response.getText());
	}

	// ------------------ route to update ----------------------------
	public void beginRouteUpdate(WebRequest request) {
		request.setURL("serverName.com", "/contextPath", "/a/123456", null, null);
		request.addParameter(ResourceRouter.HTTP_INNER_METHOD, "update", WebRequest.POST_METHOD);
	}

	public void testRouteUpdate() throws ServletException, IOException {
		getRouter().handleRequest(request, response);
	}

	public void endRouteUpdate(WebResponse response) {
		assertEquals(200, response.getStatusCode());
		assertEquals("123456update", response.getText());
	}

	// ------------------ route to delete ----------------------------
	public void beginRouteDelete(WebRequest request) {
		request.setURL("serverName.com", "/contextPath", "/a/123456", null, null);
		request.addParameter(ResourceRouter.HTTP_INNER_METHOD, "delete", WebRequest.POST_METHOD);
	}

	public void testRouteDelete() throws ServletException, IOException {
		getRouter().handleRequest(request, response);
	}

	public void endRouteDelete(WebResponse response) {
		assertEquals(200, response.getStatusCode());
		assertEquals("123456delete", response.getText());
	}

	// ------------------ route to homepage ----------------------------
	public void beginRouteHomepage(WebRequest request) {
		request.setURL("serverName.com", "/contextPath", "/", null, null);
		request.addParameter(ResourceRouter.HTTP_INNER_METHOD, "delete", WebRequest.POST_METHOD);
	}

	public void testRouteHomepage() throws ServletException, IOException {
		getRouter().handleRequest(request, response);
	}

	public void endRouteHomepage(WebResponse response) {
		assertEquals(200, response.getStatusCode());
		assertEquals("defaulthomepage", response.getText());
	}
}
