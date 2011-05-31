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
public class PeopleAlreadyExistException extends ErrorCodeException {

	private static final long serialVersionUID = 2186986728958149017L;

	public PeopleAlreadyExistException() {
		this(null);
	}

	public PeopleAlreadyExistException(String error) {
		super(ErrorCode.EC_PEOPLE_ALREADY_EXIST, error);
	}

	public PeopleAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

}
