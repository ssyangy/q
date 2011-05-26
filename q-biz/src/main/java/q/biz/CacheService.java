/**
 * 
 */
package q.biz;

/**
 * @author seanlinwang at gmail dot com
 * @date May 6, 2011
 * 
 */
public interface CacheService {

	/**
	 * @param key
	 * @param peopleId
	 * @param timestamp
	 */
	void putPasswordResetToken(String key, long peopleId, long timestamp);

	/**
	 * @param key
	 * @return
	 */
	PasswordResetToken getPasswordResetToken(String key);

	/**
	 * @param key
	 */
	void removePasswordResetToken(String key);

	/**
	 * 
	 */
	void clearMessageNotify(long peopleId);

	/**
	 * 
	 */
	void clearWeiboReplyNotify(long peopleId);

	/**
	 * 
	 */
	void clearFoNotify(long peopleId);

	/**
	 * 
	 */
	void clearAtNotify(long peopleId);

}
