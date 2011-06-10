/**
 *
 */
package q.biz;

import java.sql.SQLException;
import java.util.Map;

import q.domain.Weibo;
import q.domain.WeiboReply;

/**
 * @author seanlinwang at gmail dot com
 * @date May 5, 2011
 *
 */
public interface WeiboService {

	/**
	 * @param reply
	 * @throws Exception
	 */
	void addWeiboReply(WeiboReply reply) throws Exception;

	/**
	 * @param weibo
	 * @param groupId
	 * @throws Exception
	 */
	void addWeibo(Weibo weibo, long groupId) throws Exception;

	/**
	 * @param retweet
	 * @param groupId
	 * @throws Exception
	 */
	void addWeiboRetweet(Weibo retweet) throws Exception;

	/**
	 * @param retweet
	 * @param groupId
	 * @throws Exception
	 */
	void addReplyRetweet(Weibo retweet) throws Exception;

	/**
	 * @param peopleId
	 * @param loginPeopleId
	 * @param size
	 * @param startId
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> getPeopleWeiboPagination(long peopleId, long loginPeopleId, int size, long startId, int type) throws Exception;

	/**
	 * @param groupId
	 * @param loginPeopleId
	 * @param size
	 * @param startId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getGroupWeiboPagination(long groupId, long loginPeopleId, int size, long startId, int type) throws Exception;

	/**
	 * @param loginPeopleId
	 * @param page
	 * @param size
	 * @param startId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getWeiboReplyPagination(long weiboId, long loginPeopleId, int size, long startId, int type) throws Exception;

	/**
	 * @param loginPeopleId
	 * @param size
	 * @param startId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getReplyReceivedPagination(long loginPeopleId, int size, long startId, int type) throws Exception;

	/**
	 * @param loginPeopleId
	 * @param size
	 * @param startId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> getReplySendedPagination(long loginPeopleId, int size, long startId, int type) throws Exception;

	/**
	 * @param loginPeopleId
	 * @param size
	 * @param startId
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> getFavoritePagination(long loginPeopleId, int size, long startId, int type) throws Exception;

	/**
	 * @param weiboId
	 * @param loginPeopleId
	 * @return
	 * @throws SQLException
	 */
	Weibo getWeibo(long weiboId, long loginPeopleId) throws Exception;

	/**
	 * @param username
	 * @param loginPeopleId
	 * @param size
	 * @param startId
	 * @param type
	 * @return
	 */
	Map<String, Object> getAtPagination(long loginPeopleId, int size, long startId) throws Exception;

	Map<String, Object> getSearchWeiboPagination(String search, long loginPeopleId, int size, long startId, int type) throws Exception;

}
