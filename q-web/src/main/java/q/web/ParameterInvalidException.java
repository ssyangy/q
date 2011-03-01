/**
 * 
 */
package q.web;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 * 
 */
public class ParameterInvalidException extends Exception {

	private static final long serialVersionUID = -4160313190208497763L;

	public ParameterInvalidException() {
		
	}

	/**
	 * @param string
	 * @param fromId
	 */
	public ParameterInvalidException(String name, long value) {
		super(name + ":" + value);
	}
}
