/**
 * 
 */
package q.web.favorite;

import q.dao.FavoriteDao;
import q.dao.WeiboDao;
import q.domain.Favorite;
import q.domain.Weibo;
import q.web.ParameterInvalidException;
import q.web.Resource;
import q.web.ResourceContext;

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
		long creatorId = context.getLoginPeopleId();
		Favorite old = this.favoriteDao.getWeiboFavoriteByWeiboIdAndCreatorId(weiboId, creatorId);
		if (old == null) {
			Weibo weibo = this.weiboDao.getWeiboById(weiboId);
			if (null == weibo) {
				throw new ParameterInvalidException("fromId", weiboId);
			}
			this.favoriteDao.addWeiboFavorite(weiboId, creatorId);
		} else if(old.isUnFav()) {
			this.favoriteDao.favWeiboById(old.getId());
		}
		context.redirectReffer();
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long weiboId = context.getResourceIdLong();
		if (weiboId == 0) {
			throw new ParameterInvalidException("fromId", weiboId);
		}
	}

}