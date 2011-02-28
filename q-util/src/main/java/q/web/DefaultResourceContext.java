package q.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import q.log.Logger;
import q.util.IdCreator;
import q.util.StringKit;

public class DefaultResourceContext implements ResourceContext {
	private static final Logger log = Logger.getLogger();

	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private String[] paths;

	public DefaultResourceContext(HttpServletRequest request, HttpServletResponse response, String[] paths) {
		this.request = request;
		this.response = response;
		this.paths = paths;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	@Override
	public String[] getStringArray(String key) {
		String[] values = request.getParameterValues(key);
		return values;
	}

	@Override
	public String getString(String key) {
		return request.getParameter(key);
	}

	@Override
	public int getInt(String key) {
		return Integer.valueOf(getString(key));
	}

	@Override
	public long getIdLong(String key) {
		String value = getString(key);
		return getIdLongFromValue(value);
	}

	private long getIdLongFromValue(String value) {
		long v = -1;
		if (StringKit.isEmpty(value)) {
			return v;
		}
		try {
			v = Long.valueOf(value);
			if (!IdCreator.isValidIds(v)) {
				v = -1;
			}
		} catch (NumberFormatException e) {
		}
		return v;
	}

	@Override
	public String getRequestPath() {
		return request.getServletPath();
	}

	@Override
	public String getResourceId() {
		if (paths.length >= 2) {
			return paths[1];
		} else {
			return null;
		}
	}

	@Override
	public long getResourceIdLong() {
		return getIdLongFromValue(getResourceId());
	}

	@Override
	public void setModel(String key, Object value) {
		request.setAttribute(key, value);
		log.debug("set context model[%s:%s]", key, value);
	}

	/*
	 * 
	 * 
	 * @see q.web.ResourceContext#getLoginPeopleId()
	 */
	@Override
	public long getLoginPeopleId() {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if ("u".equals(cookie.getName())) { // login user
					if (StringKit.isNotEmpty(cookie.getValue())) {
						return Long.valueOf(cookie.getValue());
					}
				}
			}
		}
		return -1;
	}

	public void setLoginToken(long userId, String username) {
		Cookie cookie = new Cookie("u", String.valueOf(userId));
		cookie.setMaxAge(3600 * 24 * 7); // one week
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public void removeLoginToken() {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if ("u".equals(cookies[i].getName())) {
					Cookie cookie = new Cookie("u", null);
					cookie.setMaxAge(0);// clear login token
					cookie.setPath("/");
					response.addCookie(cookie);
					break;
				}
			}
		}
	}

	/*
	 * redirect to reffer
	 * 
	 * @see q.web.ResourceContext#redirectRefferUrl()
	 */
	@Override
	public void redirectReffer() throws IOException {
		redirectContextPath(request.getHeader("refferr"));
	}

	@Override
	public void redirectServletPath(String path) throws IOException {
		redirectContextPath(request.getContextPath() + path);
	}

	@Override
	public void redirectContextPath(String fullPath) throws IOException {
		response.sendRedirect(fullPath);
		log.debug("send redirect to %s", fullPath);
	}

	private boolean isEmptyView = false;

	@Override
	public void emptyView() {
		this.isEmptyView = true;
	}

	public boolean isEmptyView() {
		return isEmptyView;
	}

	@Override
	public boolean isCommitted() {
		return response.isCommitted();
	}

	@Override
	public void forward(String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
		log.debug("forward to %s", path);
	}

}