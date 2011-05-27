/**
 * 
 */
package q.web.exception;

import q.web.ErrorCodeException;


/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 9, 2011
 *
 */
public class PeopleNotLoginException extends ErrorCodeException {

	private static final long serialVersionUID = -7721993473327388655L;

	public PeopleNotLoginException(String error) {
		super("40002", error);
	}

	public PeopleNotLoginException(String message, Throwable cause) {
		super(message, cause);
	}
	

}
