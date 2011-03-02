/**
 * 
 */
package q.domain;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 * 
 */
public enum FavoriteStatus {
	UNFAV(0), FAV(1);

	private int value;

	private FavoriteStatus(int value) {
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
		if (value == FAV.value) {
			return FAV;
		} else if (value == UNFAV.value) {
			return UNFAV;
		} else {
			return null;
		}
	}

	/**
	 * @return
	 */
	public boolean isFav() {
		return this == FAV;
	}

	/**
	 * @return
	 */
	public boolean isUnFav() {
		return this == UNFAV;
	}

}
