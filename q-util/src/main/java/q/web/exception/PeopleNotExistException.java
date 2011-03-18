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
public class PeopleNotExistException extends ErrorCodeException {

	private static final long serialVersionUID = 2186986728958149017L;

	public PeopleNotExistException(String error) {
		super("40000", error);
	}

	public PeopleNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
