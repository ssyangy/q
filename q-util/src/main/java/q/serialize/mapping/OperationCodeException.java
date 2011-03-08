/**
 * 
 */
package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * The Class OperationCodeException.
 * 
 * @author alin
 * @date May 23, 2009
 */
public class OperationCodeException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -976857890463488377L;

	/** 错误码. */
	private String code;

	/** 错误信息. */
	private String msg;

	/**
	 * 获取错误码.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取错误信息.
	 * 
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 使用错误码和错误信息进行构造.
	 * 
	 * @param code
	 *            the code
	 * @param msg
	 *            the msg
	 */
	public OperationCodeException(String code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#toString()
	 */
	@Override
	public String toString() {
		return this.code + ":" + this.msg;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return this.toString();
	}

}
