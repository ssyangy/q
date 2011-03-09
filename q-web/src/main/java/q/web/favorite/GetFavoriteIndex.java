/**
 * 
 */
package q.web.favorite;

import java.util.List;

import q.biz.exception.RequestParameterInvalidException;
import q.dao.DaoHelper;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.FavoritePage;
import q.domain.Favorite;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 1, 2011
 *
 */
public class GetFavoriteIndex extends Resource {
	private FavoriteDao favoriteDao;

	private PeopleDao peopleDao;

	private WeiboDao weiboDao;

	private GroupDao groupDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		FavoritePage page = new FavoritePage();
		page.setSize(20);
		page.setStartIndex(0);
		page.setCreatorId(context.getLoginPeopleId());
		List<Favorite> favorites = this.favoriteDao.getPageFavorites(page);
		DaoHelper.injectFavoritesWithSource(peopleDao, groupDao, weiboDao, favorites);
		context.setModel("favorites", favorites);
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		if(context.getLoginPeopleId() == 0) {
			throw new RequestParameterInvalidException("loginId invalid");
		}
	}

}
