/**
 *
 */
package q.web.people;

/**
 * @author Administrator
 *
 */
public class InvalidAuthcodeException extends Exception {

	private static final long serialVersionUID = 2515594153351356284L;

	/**
	 *
	 */
	public InvalidAuthcodeException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public InvalidAuthcodeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public InvalidAuthcodeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidAuthcodeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
