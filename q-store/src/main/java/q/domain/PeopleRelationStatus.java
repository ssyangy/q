/**
 * 
 */
package q.domain;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 23, 2011
 * 
 */
public enum PeopleRelationStatus {
	STRANGER(0), DELETE(1), BLOCK(2), FOLLOWING(3);

	private int value;

	private PeopleRelationStatus(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 * @return
	 */
	public static Object convertValue(int value) {
		if (value == STRANGER.value) {
			return STRANGER;
		} else if (value == DELETE.value) {
			return DELETE;
		} else if (value == BLOCK.value) {
			return BLOCK;
		} else if (value == FOLLOWING.value) {
			return FOLLOWING;
		} else {
			return null;
		}
	}

	/**
	 * @return
	 */
	public boolean isStranger() {
		return this == STRANGER;
	}

	/**
	 * @return
	 */
	public boolean isFollowing() {
		return this == FOLLOWING;
	}

	public boolean isBlock() {
		return this == BLOCK;
	}

	public boolean isDelete() {
		return this == DELETE;
	}
}
