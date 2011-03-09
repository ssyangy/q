/**
 * 
 */
package q.biz.exception;

import q.web.ErrorCodeException;


/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 9, 2011
 *
 */
public class PeopleAlreadyExistException extends ErrorCodeException {

	private static final long serialVersionUID = 2186986728958149017L;

	public PeopleAlreadyExistException(String error) {
		super("40001", error);
	}

	public PeopleAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}
	

}
