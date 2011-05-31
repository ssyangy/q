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
public class PeopleNotExistException extends ErrorCodeException {

	private static final long serialVersionUID = 2186986728958149017L;

	public PeopleNotExistException() {
		this(null);
	}

	public PeopleNotExistException(String error) {
		super(ErrorCode.EC_PEOPLE_NOT_EXIST, error);
	}

	public PeopleNotExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
