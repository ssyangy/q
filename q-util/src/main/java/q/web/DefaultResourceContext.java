package q.web;

import javax.servlet.http.HttpServletRequest;

public class DefaultResourceContext implements ResourceContext {
	private final HttpServletRequest request;
	private String path;
	private String[] segs;

	public DefaultResourceContext(HttpServletRequest request, String path, String[] segs) {
		this.request = request;
		this.path = path;
		this.segs = segs;
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
	public String getRequestPath() {
		return request.getServletPath();
	}

	@Override
	public String getResourceLastId() {
		if (segs.length >= 2) {
			return segs[1];
		} else {
			return null;
		}
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