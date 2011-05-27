/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;

import q.dao.page.FavoritePage;
import q.domain.Favorite;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 *
 */
public interface FavoriteDao {

	/**
	 * @param page
	 * @return
	 */
	List<Favorite> getPageFavorites(FavoritePage page) throws SQLException;

	/**
	 * @param fromId
	 * @param creatorId
	 * @throws SQLException 
	 */
	void unFavReplyFavorite(long replyId, long creatorId) throws SQLException;

	/**
	 * @param weiboId
	 * @param creatorId
	 */
	void unFavWeiboFavorite(long weiboId, long creatorId) throws SQLException;

	/**
	 * @param id
	 */
	void favReplyById(long id) throws SQLException;

	/**
	 * @param replyId
	 * @param creatorId
	 * @param senderId 
	 */
	void addReplyFavorite(long replyId, long creatorId, long senderId) throws SQLException;

	/**
	 * @param weiboId
	 * @param creatorId
	 * @throws SQLException
	 */
	void addWeiboFavorite(long weiboId, long creatorId, long senderId) throws SQLException;

	/**
	 * @param replyId
	 * @param creatorId
	 * @return
	 */
	Favorite getReplyFavoriteByReplyIdAndCreatorId(long replyId, long creatorId) throws SQLException;

	/**
	 * @param id
	 * @throws SQLException
	 */
	void favWeiboById(long id) throws SQLException;

	/**
	 * @param weiboId
	 * @param creatorId
	 * @return
	 * @throws SQLException
	 */
	Favorite getWeiboFavoriteByWeiboIdAndCreatorId(long weiboId, long creatorId) throws SQLException;

	/**
	 * @param replyIds
	 * @param creatorId
	 * @return
	 * @throws SQLException
	 */
	List<Long> getFavoriteIdsByFromIdsAndCreatorId(List<Long> replyIds, long creatorId) throws SQLException;

}
