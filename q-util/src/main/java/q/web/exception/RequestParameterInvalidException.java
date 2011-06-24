/**
 * 
 */
package q.web.exception;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 * 
 */
public class RequestParameterInvalidException extends ErrorCodeException {

	private static final long serialVersionUID = -4160313190208497763L;

	public RequestParameterInvalidException() {
		this(null);
	}

	public RequestParameterInvalidException(String error) {
		super(ErrorCode.EC_REQUEST_PARAMETER_INVALID, error);
	}

	public RequestParameterInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

}
