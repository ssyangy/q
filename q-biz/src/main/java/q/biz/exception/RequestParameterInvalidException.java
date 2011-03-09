/**
 * 
 */
package q.biz.exception;

import q.web.ErrorCodeException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 * 
 */
public class RequestParameterInvalidException extends ErrorCodeException {

	private static final long serialVersionUID = -4160313190208497763L;

	public RequestParameterInvalidException(String error) {
		super("20001", error);
	}

	public RequestParameterInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	

}
