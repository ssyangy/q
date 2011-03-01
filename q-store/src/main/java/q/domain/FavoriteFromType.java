/**
 * 
 */
package q.domain;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 27, 2011
 * 
 */
public enum FavoriteFromType {
	WEIBO(0), REPLY(1);

	private int value;

	private FavoriteFromType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	/**
	 * @param int1
	 * @return
	 */
	public static FavoriteFromType convertValue(int value) {
		FavoriteFromType type = null;
		if (WEIBO.getValue() == value) {
			type = WEIBO;
		} else if (REPLY.getValue() == value) {
			type = REPLY;
		}
		return type;
	}

	public boolean isFromWeibo() {
		return this == WEIBO;
	}

	public boolean isFromReply() {
		return this == REPLY;
	}

	/**
	 * @param fromTypeId
	 * @return
	 */
	public static boolean isValid(int fromTypeId) {
		return  convertValue(fromTypeId) != null;
	}
}
