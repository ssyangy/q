/**
 * 
 */
package q.serialize.performance;

/**
 * @author xalinx at gmail dot com
 * @date Apr 1, 2010
 */
public abstract class AbstractTask {
	
	public abstract Object convertResponse() throws Exception;
	
	public abstract Object[] convertRequest() throws Exception;
	
	public abstract void setXmlFormat();

}
