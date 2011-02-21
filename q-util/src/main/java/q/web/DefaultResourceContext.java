package q.web;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import q.util.StringKit;

public class DefaultResourceContext implements ResourceContext {
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private String[] paths;

	public DefaultResourceContext(HttpServletRequest request, HttpServletResponse response, String[] paths) {
		this.request = request;
		this.response = response;
		this.paths = paths;
	}

	@Override
	public String[] getStringArray(String key) {
		String[] values = request.getParameterValues(key);
		return values;
	}

	@Override
	public String getString(String key) {
		String value = request.getParameter(key);
		return value;
	}

	@Override
	public int getInt(String key) {
		return Integer.valueOf(getString(key));
	}

	@Override
	public long getLong(String key) {
		return Long.valueOf(getString(key));
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
		return Long.valueOf(getResourceId());
	}

	@Override
	public void setModel(String key, Object value) {
		request.setAttribute(key, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.ResourceContext#getLoginPeopleId()
	 */
	@Override
	public long getLoginPeopleId() {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if ("u".equals(cookie.getName())) { // login user
				if (StringKit.isNotEmpty(cookie.getValue())) {
					return Long.valueOf(cookie.getValue());
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

	/*
	 * redirect to reffer
	 * 
	 * @see q.web.ResourceContext#redirectRefferUrl()
	 */
	@Override
	public void redirectReffer() throws IOException {
		redirect(request.getHeader("refferr"));
	}

	@Override
	public void redirectPath(String path) throws IOException {
		redirect(request.getContextPath() + path);
	}

	@Override
	public void redirect(String fullPath) throws IOException {
		response.sendRedirect(fullPath);
	}
}