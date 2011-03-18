/**
 * 
 */
package q.web.favorite;

import q.dao.FavoriteDao;
import q.dao.WeiboDao;
import q.domain.Favorite;
import q.domain.WeiboReply;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 * 
 */
public class AddReplyFavorite extends Resource {

	private FavoriteDao favoriteDao;

	private WeiboDao weiboDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long replyId = context.getResourceIdLong();
		long creatorId = context.getCookiePeopleId();
		Favorite old = this.favoriteDao.getReplyFavoriteByReplyIdAndCreatorId(replyId, creatorId);
		if (old == null) {
			WeiboReply reply = this.weiboDao.getWeiboReplyById(replyId);
			if (null == reply) {
				throw new RequestParameterInvalidException("fromId:" + replyId);
			}
			this.favoriteDao.addReplyFavorite(replyId, creatorId);
		} else if (old.isUnFav()) {
			this.favoriteDao.favReplyById(old.getId());
		}
		context.redirectReffer();
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long replyId = context.getResourceIdLong();
		if (replyId == 0) {
			throw new RequestParameterInvalidException("fromId:" + replyId);
		}
	}

}
