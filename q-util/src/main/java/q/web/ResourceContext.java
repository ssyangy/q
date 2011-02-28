package q.web;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 * 
 */
public interface ResourceContext {

	String[] getStringArray(String key);

	String getString(String key);

	long getLongId(String key);

	int getInt(String key);

	String getRequestPath();

	String getResourceId();

	long getResourceIdLong();

	/**
	 * @return
	 */
	long getLoginPeopleId();

	void setModel(String key, Object value);

	/**
	 * Redirect to reffer url.
	 * 
	 * @throws IOException
	 */
	void redirectReffer() throws IOException;

	/**
	 * @param contextPath
	 * @throws IOException
	 */
	void redirectContextPath(String contextPath) throws IOException;

	/**
	 * @param servletPath
	 * @throws IOException
	 */
	void redirectServletPath(String servletPath) throws IOException;

	/**
	 * 
	 */
	void emptyView();

	boolean isEmptyView();

	/**
	 * @return
	 */
	boolean isCommitted();

	/**
	 * @param string
	 * @throws Exception
	 */
	void forward(String string) throws ServletException, IOException;
}
