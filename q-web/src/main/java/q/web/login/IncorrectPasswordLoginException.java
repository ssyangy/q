/**
 * 
 */
package q.web.login;

import q.web.ErrorCodeException;

/**
 * Resource login exception.
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 20, 2011
 * 
 */
public class IncorrectPasswordLoginException extends ErrorCodeException {
	private static final long serialVersionUID = -3080434513698835125L;

	public IncorrectPasswordLoginException(String error) {
		super("40003", error);
	}

	public IncorrectPasswordLoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
