/**
 * 
 */
package q.serialize.mapping;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * The Class ExceptionExpress.
 */
public class ExceptionExpressInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 917909775338299630L;

	/** The fail key. */
	private String failKey = null;

	/** The fail value. */
	private Object failValue = null;

	/** The fail code key. */
	private String failCodeKey = null;

	/** The fail msg key. */
	private String failMsgKey = null;

	/**
	 * The Constructor.
	 * 
	 * @param failKey
	 *            the fail key
	 * @param failValue
	 *            the fail value
	 * @param failCodeKey
	 *            the fail code key
	 * @param failMsgKey
	 *            the fail msg key
	 */
	public ExceptionExpressInfo(String failKey, Object failValue,
			String failCodeKey, String failMsgKey) {
		super();
		this.failKey = failKey;
		this.failValue = failValue;
		this.failCodeKey = failCodeKey;
		this.failMsgKey = failMsgKey;
	}

	/**
	 * Gets the fail msg key.
	 * 
	 * @return the fail msg key
	 */
	public String getFailMsgKey() {
		return failMsgKey;
	}

	/**
	 * Sets the fail msg key.
	 * 
	 * @param failMsgKey
	 *            the new fail msg key
	 */
	public void setFailMsgKey(String failMsgKey) {
		this.failMsgKey = failMsgKey;
	}

	/**
	 * Gets the fail code key.
	 * 
	 * @return the fail code key
	 */
	public String getFailCodeKey() {
		return failCodeKey;
	}

	/**
	 * Sets the fail code key.
	 * 
	 * @param codeKey
	 *            the new fail code key
	 */
	public void setFailCodeKey(String codeKey) {
		this.failCodeKey = codeKey;
	}

	/**
	 * Gets the fail key.
	 * 
	 * @return the fail key
	 */
	public String getFailKey() {
		return failKey;
	}

	/**
	 * Sets the fail key.
	 * 
	 * @param failKey
	 *            the new fail key
	 */
	public void setFailKey(String failKey) {
		this.failKey = failKey;
	}

	/**
	 * Gets the fail value.
	 * 
	 * @return the fail value
	 */
	public Object getFailValue() {
		return failValue;
	}

	/**
	 * Sets the fail value.
	 * 
	 * @param failValue
	 *            the new fail value
	 */
	public void setFailValue(Object failValue) {
		this.failValue = failValue;
	}

	/**
	 * Creates the.
	 * 
	 * @param express
	 *            the express
	 * 
	 * @return the exception express
	 */
	public static ExceptionExpressInfo create(String express) {
		String[] segs = StringUtils.split(express, ',');
		String[] failExpress = StringUtils.split(segs[0], '=');
		String failKey = failExpress[0];
		Boolean failValue = Boolean.valueOf(failExpress[1]);
		String failCodeKey = segs[1];
		String failMsgKey = segs[2];
		ExceptionExpressInfo ee = new ExceptionExpressInfo(failKey.trim(), failValue,
				failCodeKey.trim(), failMsgKey.trim());
		return ee;
	}

	/**
	 * Need exception.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return true, if successful
	 */
	public boolean needException(Object value) {
		return this.failValue.equals(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.failKey).append('=').append(
				this.failValue.toString().trim()).append(',').append(
				this.failCodeKey.trim()).append(',').append(
				this.failMsgKey.trim());
		return sb.toString();
	}
}