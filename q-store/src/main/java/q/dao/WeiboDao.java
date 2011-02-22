/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;

import q.domain.Weibo;
import q.domain.WeiboPage;

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
	List<Weibo> getPageWeibo(WeiboPage page) throws SQLException;

}
