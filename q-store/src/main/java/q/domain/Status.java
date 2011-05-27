/**
 * 
 */
package q.domain;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 19, 2011
 * 
 */
public enum Status {
	COMMON(0), DELETE(1), BLOCK(2);

	private int value;

	private Status(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
