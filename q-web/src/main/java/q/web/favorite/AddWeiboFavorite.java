/**
 * 
 */
package q.web.favorite;

import q.dao.FavoriteDao;
import q.dao.WeiboDao;
import q.domain.Favorite;
import q.domain.Weibo;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.PeopleNotPermitException;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 * 
 */
public class AddWeiboFavorite extends Resource {

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
		long weiboId = context.getResourceIdLong();
		long creatorId = context.getCookiePeopleId();
		Favorite old = this.favoriteDao.getWeiboFavoriteByWeiboIdAndCreatorId(weiboId, creatorId);
		if (old == null) {
			Weibo weibo = this.weiboDao.getWeiboById(weiboId);
			if (null == weibo) {
				throw new RequestParameterInvalidException("weibo:not exist " + weiboId);
			}
			this.favoriteDao.addWeiboFavorite(weiboId, creatorId, weibo.getSenderId());
		} else if (old.isUnFav()) {
			this.favoriteDao.favWeiboById(old.getId());
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
		long weiboId = context.getResourceIdLong();
		if (weiboId == 0) {
			throw new RequestParameterInvalidException("weibo:" + weiboId);
		}
	}

}
