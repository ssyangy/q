package q.web;

import java.io.IOException;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 * 
 */
public interface ResourceContext {

	String[] getStringArray(String key);

	String getString(String key);

	long getLong(String key);

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
	 * @throws IOException 
	 */
	void redirectReffer() throws IOException;

	/**
	 * @param fromPath
	 * @throws IOException 
	 */
	void redirect(String fromPath) throws IOException;

	/**
	 * @param string
	 * @throws IOException 
	 */
	void redirectPath(String path) throws IOException;
}
