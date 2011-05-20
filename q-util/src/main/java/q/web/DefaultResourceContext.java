package q.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import q.log.Logger;
import q.util.ArrayKit;
import q.util.IdCreator;
import q.util.StringKit;

public class DefaultResourceContext implements ResourceContext {
	private static final Logger log = Logger.getLogger();

	private static final String MEDIA_TYPE_APPLICATION_JSON = "application/json";
	private static final String MEDIA_TYPE_APPLICATION_JSON_TEXT = "text/javascript";

	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private String[] paths;

	private String contextPath;

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

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
	public int getInt(String key, int defaultValue) {
		String value = getString(key);
		return getIntFromValue(value, defaultValue);
	}

	@Override
	public long getLong(String key, long defaultValue) {
		String value = getString(key);
		return getLongFromValue(value, defaultValue);
	}

	/**
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	private long getLongFromValue(String value, long defaultValue) {
		long v = defaultValue;
		if (StringKit.isEmpty(value)) {
			return v;
		}
		try {
			v = Long.valueOf(value);
		} catch (NumberFormatException e) {
		}
		return v;
	}

	private int getIntFromValue(String value, int defaultValue) {
		int v = defaultValue;
		if (StringKit.isEmpty(value)) {
			return v;
		}
		try {
			v = Integer.valueOf(value);
		} catch (NumberFormatException e) {
		}
		return v;
	}

	@Override
	public long getIdLong(String key) {
		String value = getString(key);
		return getIdLongFromValue(value);
	}

	@Override
	public long getIdLong(String key, long defaultValue) {
		long id = this.getIdLong(key);
		if (id <= 0) {
			id = defaultValue;
		}
		return id;
	}

	@Override
	public List<Long> getIdLongList(String key) {
		String[] values = request.getParameterValues(key);
		if (ArrayKit.isEmpty(values)) {
			return null;
		}
		List<Long> list = null;
		for (String value : values) {
			long id = getIdLongFromValue(value);
			if (id > 0) {
				if (list == null) {
					list = new ArrayList<Long>(values.length);
				}
				list.add(id);
			}
		}
		return list;
	}

	private long getIdLongFromValue(String value) {
		long v = 0;
		if (StringKit.isEmpty(value)) {
			return v;
		}
		try {
			v = Long.valueOf(value);
			if (!IdCreator.isValidIds(v)) {
				v = 0;
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

	@Override
	public Object getModel(String key) {
		return request.getAttribute(key);
	}

	private LoginCookie loginCookie;

	private static final LoginCookie EMPTY_LOGIN_COOKIE = LoginCookie.getEmpty();

	@Override
	public LoginCookie getLoginCookie() {
		if (loginCookie == null) {
			loginCookie = LoginCookie.get(request, response);
			if (loginCookie == null) {
				loginCookie = EMPTY_LOGIN_COOKIE;
			}
		}
		return loginCookie;
	}

	@Override
	public boolean isLogin() {
		return getLoginCookie() != EMPTY_LOGIN_COOKIE;
	}

	/*
	 * 
	 * 
	 * @see q.web.ResourceContext#getLoginPeopleId()
	 */
	@Override
	public long getCookiePeopleId() {
		LoginCookie loginCookie = getLoginCookie();
		long peopleId = loginCookie.getPeopleId();
		return peopleId;
	}

	public void addLoginCookie(LoginCookie loginCookie) {
		loginCookie.add(response);
		this.loginCookie = null;
	}

	public void removeLoginCookie() {
		LoginCookie.delete(response);
		this.loginCookie = null;
	}

	/*
	 * redirect to reffer
	 * 
	 * @see q.web.ResourceContext#redirectRefferUrl()
	 */
	@Override
	public void redirectReffer() throws IOException {
		redirectContextPath(request.getHeader("Referer"));
	}

	@Override
	public void redirectServletPath(String path) throws IOException {
		redirectContextPath(getContextPath() + path);
	}

	@Override
	public void redirectContextPath(String fullPath) throws IOException {
		log.debug("send redirect to %s", fullPath);
		response.sendRedirect(fullPath);
	}

	private boolean isEmptyView = false;

	private ErrorCodeException errorCodeException;

	private String urlPrefix;

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

	public OutputStream getOutputStream() throws IOException {
		return response.getOutputStream();
	}

	@Override
	public Writer getWriter() throws IOException {
		return response.getWriter();

	}

	@Override
	public void setErrorModel(ErrorCodeException e) {
		this.errorCodeException = e;
	}

	@Override
	public ErrorCodeException getErrorModel() {
		return this.errorCodeException;
	}

	@Override
	public boolean isApiRequest() {
		String accept = request.getHeader("Accept");
		boolean isApi = false;
		if (accept != null && (accept.startsWith(MEDIA_TYPE_APPLICATION_JSON) || accept.startsWith(MEDIA_TYPE_APPLICATION_JSON_TEXT))) { // do json mime
			isApi = true;
		}
		return isApi;
	}

	/**
	 * @param urlPrefix
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}

	@Override
	public String getUrlPrefix() {
		return this.urlPrefix;
	}

}