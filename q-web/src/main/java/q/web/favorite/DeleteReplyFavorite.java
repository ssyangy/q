/**
 * 
 */
package q.web.favorite;

import q.dao.FavoriteDao;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 * 
 */
public class DeleteReplyFavorite extends Resource {

	private FavoriteDao favoriteDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long creatorId = context.getCookiePeopleId();
		long replyId = context.getResourceIdLong();
		this.favoriteDao.unFavReplyFavorite(replyId, creatorId);
	}

	@Override
	public void validate(ResourceContext context) throws Exception {

	}

}