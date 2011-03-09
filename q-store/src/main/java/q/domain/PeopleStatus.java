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
public enum PeopleStatus {
	COMMON(0), DELETE(1), BLOCK(2), UNACTIVE(3), MISSING_PROFILE(4);

	private int value;

	private PeopleStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
