/**
 * 
 */
package q.web;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 9, 2011
 * 
 */
public abstract class ErrorCodeException extends Exception {

	private static final long serialVersionUID = -2225318757929246527L;

	protected String errorCode;

	protected String error;
	
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	protected ErrorCodeException(String errorCode, String error) {
		this.errorCode = errorCode;
		this.error = error;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ErrorCodeException(String message, Throwable cause) {
		super(cause);
		this.error = message;
	}

}
