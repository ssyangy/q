package q.web;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 * 
 */
public interface ResourceContext {

	String[] getStringArray(String key);

	String getString(String key);

	long getIdLong(String key);

	int getInt(String key, int defaultValue);

	String getRequestPath();

	long getResourceIdLong();
	
	String getResourceId();

	/**
	 * @return
	 */
	long getCookiePeopleId();
	
	LoginCookie getLoginCookie();

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

	/**
	 * @param key
	 * @return
	 */
	Object getModel(String key);

	/**
	 * @return
	 * @throws IOException 
	 */
	Writer getWriter() throws IOException;

	/**
	 * @param e
	 */
	void setErrorModel(ErrorCodeException e);

	/**
	 * @return
	 */
	ErrorCodeException getErrorModel();
}
