/**
 * 
 */
package q.web.exception;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 9, 2011
 * 
 */
public class PeopleNotLoginException extends ErrorCodeException {

	private static final long serialVersionUID = -7721993473327388655L;

	public PeopleNotLoginException() {
		this(null);
	}

	public PeopleNotLoginException(String error) {
		super(ErrorCode.EC_PEOPLE_NOT_LOGIN, error);
	}

	public PeopleNotLoginException(String message, Throwable cause) {
		super(message, cause);
	}

}
