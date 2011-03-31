/**
 * 
 */
package q.web.favorite;

import java.util.ArrayList;
import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.FavoritePage;
import q.domain.Favorite;
import q.domain.WeiboModel;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;
import q.web.people.GetPeopleFrame;

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
	
	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

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
		long loginPeopleId = context.getCookiePeopleId();
		
		GetPeopleFrame frame = new GetPeopleFrame();
		frame.setEventDao(eventDao);
		frame.setGroupDao(groupDao);
		frame.setPeopleDao(peopleDao);
		frame.setWeiboDao(weiboDao);
		frame.validate(context);
		frame.execute(context);
		
		FavoritePage page = new FavoritePage();
		page.setSize(20);
		page.setStartIndex(0);
		page.setCreatorId(loginPeopleId);
		List<Favorite> favorites = this.favoriteDao.getPageFavorites(page);
		DaoHelper.injectFavoritesWithSource(weiboDao, favorites);
		List<WeiboModel> weiboModels = new ArrayList<WeiboModel>(favorites.size());
		for(Favorite fav: favorites) {
			weiboModels.add(fav.getSource());
		}
		DaoHelper.injectWeiboModelsWithFrom(groupDao, weiboModels);
		DaoHelper.injectWeiboModelsWithQuote(weiboDao, weiboModels);
		DaoHelper.injectWeiboModelsWithPeople(peopleDao, weiboModels);
		context.setModel("weibos", weiboModels);
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		if(context.getCookiePeopleId() == 0) {
			throw new RequestParameterInvalidException("loginId invalid");
		}
	}

}
