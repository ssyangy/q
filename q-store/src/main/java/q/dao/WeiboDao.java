/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;

import q.dao.page.WeiboPage;
import q.dao.page.WeiboReplyPage;
import q.domain.Weibo;
import q.domain.WeiboJoinGroup;
import q.domain.WeiboReply;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 21, 2011
 * 
 */
public interface WeiboDao {

	/**
	 * @param weibo
	 * @throws SQLException
	 */
	void addWeibo(Weibo weibo) throws SQLException;

	/**
	 * @param weiboId
	 * @param groupId
	 * @throws SQLException
	 */
	void addWeiboJoinGroup(long weiboId, long groupId) throws SQLException;

	/**
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	List<Weibo> getPageGroupWeibo(WeiboPage page) throws SQLException;
	
	/**
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	List<Weibo> getPageGroupFeedWeibo(WeiboPage page) throws SQLException;

	/**
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	List<Weibo> getPageWeibo(WeiboPage page) throws SQLException;

	/**
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	List<Weibo> getPageFollowingWeibos(WeiboPage page) throws SQLException;

	/**
	 * @param weiboId
	 * @return
	 */
	Weibo getWeiboById(long weiboId) throws SQLException;

	/**
	 * @param page
	 * @return
	 */
	List<WeiboReply> getPageWeiboReply(WeiboReplyPage page) throws SQLException;

	/**
	 * @param reply
	 */
	void addWeiboReply(WeiboReply reply) throws SQLException;

	/**
	 * @param peopleId
	 * @return
	 */
	int getPeopleWeiboNumByPeopleId(long peopleId) throws SQLException;

	/**
	 * @param replyWeiboId
	 * @return
	 * @throws SQLException
	 */
	WeiboReply getWeiboReplyById(long weiboReplyId) throws SQLException;

	/**
	 * @param id
	 * @param groupId
	 * @return
	 * @throws SQLException
	 */
	WeiboJoinGroup getWeiboJoinGroupByWeiboIdAndGroupId(long id, long groupId) throws SQLException;

	/**
	 * @param id
	 * @return
	 */
	WeiboJoinGroup getWeiboJoinGroupByWeiboId(long weiboId) throws SQLException;

	/**
	 * @param ids
	 * @param needDesc
	 * @return
	 * @throws SQLException
	 */
	List<Weibo> getWeibosByIds(List<Long> ids, boolean needDesc) throws SQLException;

	/**
	 * @param sourceReplyIds
	 * @param b
	 * @return
	 */
	List<WeiboReply> getWeiboRepliesByIds(List<Long> replyIds, boolean needDesc) throws SQLException;

}
