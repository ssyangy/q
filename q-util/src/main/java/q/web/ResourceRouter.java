/**
 * 
 */
package q.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import q.log.Logger;
import q.util.StringKit;

/**
 * RESTFUL resource entry
 * 
 * @author seanlinwang
 * @date Jan 16, 2011
 * 
 */
public class ResourceRouter implements Controller, ApplicationContextAware {
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

	private ViewResolver viewResolver = new SpringViewResolver();

	public void setViewResolver(ViewResolver viewResolver) {
		this.viewResolver = viewResolver;
	}

	private String urlPrefix;

	private String contextPath;

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	private String staticUrlPrefix;
	
	public void setStaticUrlPrefix(String staticUrlPrefix) {
		this.staticUrlPrefix = staticUrlPrefix;
	}

	private Set<String> needLoginResources;

	public void setNeedLoginResources(Set<String> needLoginResources) {
		this.needLoginResources = needLoginResources;
	}

	private String loginPath;

	public void setLoginPath(String loginPath) {
		this.loginPath = loginPath;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getMethod().toLowerCase(); // lowercase http method
		String path = request.getRequestURI().substring(request.getContextPath().length()); // path without context and domain
		log.debug("request resource by method %s and path %s", method, path);
		String[] segs = StringKit.split(path, PATH_SPLIT); // split path to path segments
		Resource resource = getResource(request, method, path, segs); // get request resource
		if (resource == null) { // if resource not exists , return 404
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			log.info("resource not found by method %s and path %s", method, path);
			return null;
		} else {
			ResourceContext context = toResourceContext(request, response, path, segs); // construct resource context
			if (this.needLoginResources != null && this.needLoginResources.contains(resource.getName())) { // request resource need visitor login first
				if (context.getLoginPeopleId() < 0) { // visitor logoff
					context.redirectServletPath(loginPath + "?from=" + request.getContextPath() + path);
					return null;
				}
			}

			try {
				resource.validate(context);
				resource.execute(context); // execute resource if exists
				complementModel(context); // complement model
				ModelAndView view = this.viewResolver.view(context, resource);// use resource name as view name
				return view;
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				log.error("resource execute exeption by method %s and path %s", e, method, path);
			}
			return null;
		}
	}

	private void complementModel(ResourceContext context) {
		if (this.urlPrefix != null)
			context.setModel("urlPrefix", this.urlPrefix);
		if (this.contextPath != null)
			context.setModel("contextPath", this.contextPath);
		if (this.staticUrlPrefix != null) {
			context.setModel("staticUrlPrefix", this.staticUrlPrefix);
		}
	}

	protected ResourceContext toResourceContext(final HttpServletRequest request, final HttpServletResponse response, String path, String[] segs) {
		return new DefaultResourceContext(request, response, segs);
	}

	protected Resource getResource(HttpServletRequest request, String method, String path, String[] segs) {
		Resource resource = null;
		String resourceName = toResourceName(method, segs);
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

	protected String toResourceName(String method, String[] segs) {
		String resourceName = null;
		if (segs.length == 0) {
			return resourceName;
		}
		resourceName = segs[0];
		if (segs.length == 1) {
			if (HTTP_METHOD_GET.equals(method)) {
				resourceName += "Index";
			}
		} else if (segs.length == 2) {
			String last = segs[segs.length - 1];
			if (!StringUtils.isNumeric(last)) {
				resourceName += StringKit.capitalize(last);
			}
		} else if (segs.length == 3) {
			String last = segs[segs.length - 1];
			resourceName += StringKit.capitalize(last);
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
