/**
 * 
 */
package q.web.exception;

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

	public PeopleLoginPasswordException() {
		this(null);
	}

	public PeopleLoginPasswordException(String error) {
		super(ErrorCode.EC_PEOPLE_LOGIN_PASSWORD_ERROR, error);
	}

	public PeopleLoginPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

}
