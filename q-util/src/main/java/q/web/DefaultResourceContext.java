package q.web;

import javax.servlet.http.HttpServletRequest;

public class DefaultResourceContext implements ResourceContext {
	private final HttpServletRequest request;
	private String path;

	public DefaultResourceContext(HttpServletRequest request, String path) {
		this.request = request;
		this.path = path;
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
	public String getRequestPath() {
		return request.getServletPath();
	}

	@Override
	public String getResourceLastId() {
		int lastSlashIndex = this.path.lastIndexOf(ResourceRouter.PATH_SPLIT);
		return path.substring(lastSlashIndex + 1);
	}

	@Override
	public long getResourceLastIdLong() {
		return Long.valueOf(getResourceLastId());
	}

	@Override
	public void setModel(String key, Object value) {
		request.setAttribute(key, value);
	}
}