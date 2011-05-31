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
public class PeopleNotPermitException extends ErrorCodeException {

	private static final long serialVersionUID = -7721993473327388655L;

	public PeopleNotPermitException() {
		this(null);
	}

	public PeopleNotPermitException(String error) {
		super(ErrorCode.EC_PEOPLE_NOT_PERMIT, error);
	}

	public PeopleNotPermitException(String message, Throwable cause) {
		super(message, cause);
	}

}
