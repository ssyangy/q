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
public class PeopleNotPermitException extends ErrorCodeException {

	private static final long serialVersionUID = -7721993473327388655L;

	public PeopleNotPermitException(String error) {
		super("40004", error);
	}

	public PeopleNotPermitException(String message, Throwable cause) {
		super(message, cause);
	}
	

}
