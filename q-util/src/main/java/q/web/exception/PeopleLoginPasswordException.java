/**
 * 
 */
package q.web.exception;

import q.web.ErrorCodeException;

/**
 * Resource login exception.
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 20, 2011
 * 
 */
public class PeopleLoginPasswordException extends ErrorCodeException {
	private static final long serialVersionUID = -3080434513698835125L;

	public PeopleLoginPasswordException(String error) {
		super("40003", error);
	}

	public PeopleLoginPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

}
