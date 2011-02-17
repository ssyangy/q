package q.web;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 *
 */
public interface ResourceContext {
	
	String[] getStringArray(String key);
	
	String getString(String key);
	
	String getRequestPath();
	
	String getResourceLastId();
	
	long getResourceLastIdLong();
	
	void setModel(String key, Object value);
	
}
