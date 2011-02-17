/**
 * 
 */
package q.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.HttpRequestHandler;

import q.log.Logger;
import q.util.StringKit;

/**
 * RESTFUL resource entry
 * 
 * @author seanlinwang
 * @date Jan 16, 2011
 * 
 */
public class ResourceRouter implements HttpRequestHandler, ApplicationContextAware {
	public static final char PATH_SPLIT = '/';
	public static final String HTTP_METHOD_POST = "post";
	public static final String HTTP_INNER_METHOD = "_method";
	public static final String HTTP_METHOD_UPDATE = "update";
	public static final String HTTP_METHOD_DELETE = "delete";
	public static final String HTTP_METHOD_GET = "get";
	private final static Logger log = Logger.getLogger();
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * default resource
	 */
	private Resource defaultResource;

	/**
	 * set default resource to router
	 * 
	 * @param defaultResource
	 */
	public void setDefaultResource(Resource defaultResource) {
		this.defaultResource = defaultResource;
	}

	private ViewResolver viewResolver = new JspViewResolver(); // jsp is the default view resolver

	public void setViewResolver(ViewResolver viewResolver) {
		this.viewResolver = viewResolver;
	}

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod().toLowerCase();
		String path = request.getRequestURI().substring(request.getContextPath().length()).toLowerCase();
		log.debug("request resource by method %s and path %s", method, path);
		Resource resource = getResource(request, method, path);
		// execute resource if exists
		if (resource == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			log.info("resource not found by method %s and path %s", method, path);
		} else {
			ResourceContext context = toResourceContext(request, response, path); // extract querys
			try {
				boolean correct = resource.validate(context);
				if (correct) {
					resource.execute(context);
				}
				this.viewResolver.view(request, response, resource.getName());// use resource name as view name
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				log.error("resource execute exeption by method %s and path %s", e, method, path);
			}
		}
		return;
	}

	protected ResourceContext toResourceContext(final HttpServletRequest request, final HttpServletResponse response, String path) {
		return new DefaultResourceContext(request, path);
	}

	protected Resource getResource(HttpServletRequest request, String method, String path) {
		Resource resource = null;
		String resourceName = toResourceName(method, path);
		if (StringKit.isEmpty(resourceName)) {
			resource = this.defaultResource;
			resource.setName("default");
		} else {
			// get resource by resourceName and method
			if (HTTP_METHOD_POST.equals(method)) {
				String _method = request.getParameter(HTTP_INNER_METHOD);
				if (null == _method) {
					resource = this.getPostResource(resourceName);
				} else {
					_method = _method.toLowerCase();
					if (HTTP_METHOD_UPDATE.equals(_method)) {
						resource = this.getUpdateResource(resourceName);
					}
					if (HTTP_METHOD_DELETE.equals(_method)) {
						resource = this.getDeleteResource(resourceName);
					}
				}
			} else if (HTTP_METHOD_GET.equals(method)) {
				resource = this.getGetResource(resourceName);
			}
		}
		log.debug("get resource:%s by method:%s and path:%s, resourceName:%s", resource, method, path, resourceName);
		return resource;
	}

	protected String toResourceName(String method, String path) {
		String resourceName = null;
		String[] segs = StringKit.split(path, PATH_SPLIT);
		if (segs.length == 0) {
			return resourceName;
		}
		resourceName = segs[0];
		if (segs.length == 1) {
			if (HTTP_METHOD_GET.equals(method)) {
				resourceName += "Index";
			}
		} else {
			String last = segs[segs.length-1].toLowerCase();
			if ("new".equals(last)) {
				resourceName += "New";
			} else if ("edit".equals(last)) {
				resourceName += "Edit";
			}
		}
		return resourceName;
	}

	private Resource getGetResource(String resourceName) {
		return getResource("get", resourceName);
	}

	private Resource getUpdateResource(String resourceName) {
		return getResource("update", resourceName);
	}

	private Resource getPostResource(String resourceName) {
		return getResource("add", resourceName);
	}

	private Resource getDeleteResource(String resourceName) {
		return getResource("delete", resourceName);
	}

	private Resource getResource(String prefix, String resourceName) {
		Resource resource = null;
		String beanName = prefix + StringKit.capitalize(resourceName);
		Object bean = null;
		try {
			bean = this.applicationContext.getBean(beanName);
		} catch (NoSuchBeanDefinitionException e) {
			log.debug("no such bean %s", beanName);
		} catch (Exception e) {
			log.error("get bean error", e);
		}
		if (bean instanceof Resource) {
			resource = (Resource) bean;
			resource.setName(beanName);
		}
		return resource;
	}

}
