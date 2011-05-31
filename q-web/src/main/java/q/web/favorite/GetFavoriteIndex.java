/**
 * 
 */
package q.web.favorite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.FavoritePage;
import q.domain.Favorite;
import q.domain.FavoriteStatus;
import q.domain.WeiboModel;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();

		if (!context.isApiRequest()) {
			GetPeopleFrame frame = new GetPeopleFrame();
			frame.setEventDao(eventDao);
			frame.setGroupDao(groupDao);
			frame.setPeopleDao(peopleDao);
			frame.validate(context);
			frame.execute(context);
		} else { // for api
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId", IdCreator.MAX_ID);
			int type = context.getInt("type", 0);
			int asc = 1;
			FavoritePage page = new FavoritePage();
			if (type == asc) { // 1 indicate asc
				page.setDesc(false);
			} else {
				page.setDesc(true);
			}
			boolean hasPrev = false;
			boolean hasNext = false;
			int fetchSize = size + 1;
			page.setStatus(FavoriteStatus.FAV);
			page.setSize(fetchSize);
			page.setCreatorId(loginPeopleId);
			if (startId > 0) {
				page.setStartId(startId);
			}
			List<Favorite> favorites = this.favoriteDao.getPageFavorites(page);
			Map<String, Object> api = new HashMap<String, Object>();
			if (CollectionKit.isNotEmpty(favorites)) {
				if (favorites.size() == fetchSize) {
					if (type == asc) { // more than one previous page
						hasPrev = true;
					} else { // more than one next page
						hasNext = true;
					}
					favorites.remove(favorites.size() - 1);// remove last one
				}
				if (type == asc) { // this action from next page
					hasNext = true;
				} else if (startId != IdCreator.MAX_ID) {// this action from previous page
					hasPrev = true;
				}
				if (type == asc) { // reverse asc to desc
					Favorite[] array = favorites.toArray(new Favorite[favorites.size()]);
					CollectionUtils.reverseArray(array);
					favorites = Arrays.asList(array);
				}

				DaoHelper.injectFavoritesWithSource(weiboDao, favorites);
				List<WeiboModel> weiboModels = new ArrayList<WeiboModel>(favorites.size());
				for (Favorite fav : favorites) {
					weiboModels.add(fav.getSource());
				}
				DaoHelper.injectWeiboModelsWithFrom(groupDao, weiboModels);
				DaoHelper.injectWeiboModelsWithQuote(weiboDao, weiboModels);
				DaoHelper.injectWeiboModelsWithPeople(peopleDao, weiboModels);
				api.put("favorites", favorites);
			}
			api.put("hasPrev", hasPrev);
			api.put("hasNext", hasNext);
			context.setModel("api", api);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
	}

}
