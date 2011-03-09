/**
 * 
 */
package q.serialize.mapping;

/**
 * 映射错误.
 * 
 * @version 2009-2-16
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class MappingException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1458404602069780643L;

	/**
	 * Instantiates a new mapping exception.
	 */
	public MappingException() {
		super();
	}

	/**
	 * The Constructor.
	 * 
	 * @param message
	 *            the message
	 * @param cause
	 *            the cause
	 */
	public MappingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * The Constructor.
	 * 
	 * @param message
	 *            the message
	 */
	public MappingException(String message) {
		super(message);
	}

	/**
	 * The Constructor.
	 * 
	 * @param cause
	 *            the cause
	 */
	public MappingException(Throwable cause) {
		super(cause);
	}

}
