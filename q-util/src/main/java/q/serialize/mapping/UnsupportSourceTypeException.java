/**
 * 
 */
package q.serialize.mapping;

/**
 * Unsupport source type when mapping
 * 
 * @author xalinx at gmail dot com
 * @date Feb 26, 2010
 */
public class UnsupportSourceTypeException extends MappingException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1031764547589590580L;

	/**
	 * 
	 */
	public UnsupportSourceTypeException() {
	}
	
	public UnsupportSourceTypeException(Class<?> clz) {
		super(clz.getCanonicalName());
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnsupportSourceTypeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public UnsupportSourceTypeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UnsupportSourceTypeException(Throwable cause) {
		super(cause);
	}

}
