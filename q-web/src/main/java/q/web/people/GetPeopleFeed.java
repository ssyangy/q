package q.web.people;

import java.util.ArrayList;
import java.util.List;

import q.biz.SearchService;
import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.FavoritePage;
import q.dao.page.WeiboPage;
import q.dao.page.WeiboReplyPage;
import q.domain.Favorite;
import q.domain.People;
import q.domain.WeiboModel;
import q.util.CollectionKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.group.GetPeopleFeedFrame;

public class GetPeopleFeed extends Resource {
	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private FavoriteDao favoriteDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
        People peopleTemp=peopleDao.getPeopleById(loginPeopleId);
		List<? extends WeiboModel> weibos = null;
		List<Long> senderIds = peopleDao.getAllFollowingId(loginPeopleId);
		senderIds.add(loginPeopleId);// add login people
		if (CollectionKit.isNotEmpty(senderIds)) {
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId");
			String tab = context.getString("tab");
			if ("at".equals(tab)) {// fixme realname id?
				List<Long> bs = searchService.searchWeibo("@"+peopleTemp.getUsername());
				if (CollectionKit.isNotEmpty(bs)) {
					weibos = weiboDao.getWeibosByIds(bs, true);
				}
			} else if ("replied".equals(tab)) {
				WeiboReplyPage page = new WeiboReplyPage();
				page.setStartIndex(0);
				page.setSize(size);
				if (startId > 0) {
					page.setStartId(startId);
				}
				page.setQuoteSenderIds(senderIds);
				page.setSenderId(loginPeopleId);
				weibos = weiboDao.getWeiboRepliesByPage(page);
				DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, weibos, loginPeopleId);
			} else if ("favorite".equals(tab)) {
				FavoritePage page = new FavoritePage();
				page.setSize(size);
				page.setStartIndex(0);
				if (startId > 0) {
					page.setStartId(startId);
				}
				page.setCreatorId(loginPeopleId);
				page.setSenderIds(senderIds);
				List<Favorite> favorites = this.favoriteDao.getPageFavorites(page);
				DaoHelper.injectFavoritesWithSource(weiboDao, favorites);
				List<WeiboModel> weiboModels = new ArrayList<WeiboModel>(favorites.size());
				for (Favorite fav : favorites) {
					weiboModels.add(fav.getSource());
				}
				weibos = weiboModels;
			} else {
				WeiboPage page = new WeiboPage();
				page.setStartIndex(0);
				page.setSize(size);
				page.setSenderIds(senderIds);
				if (startId > 0) {
					page.setStartId(startId);
				}
				weibos = weiboDao.getFollowingWeibosByPage(page);
				DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, weibos, loginPeopleId);
			}
		}

		if (CollectionKit.isNotEmpty(weibos)) {
			DaoHelper.injectWeiboModelsWithFrom(groupDao, weibos);
			DaoHelper.injectWeiboModelsWithQuote(weiboDao, weibos);
			DaoHelper.injectWeiboModelsWithPeople(peopleDao, weibos);
			context.setModel("weibos", weibos);
		}

		if (!context.isApiRequest()) {
			GetPeopleFeedFrame frame = new GetPeopleFeedFrame();
			frame.setPeopleDao(peopleDao);
			frame.validate(context);
			frame.execute(context);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {

	}

}
