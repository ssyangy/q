/**
 *
 */
package q.web.weibo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import q.biz.CacheService;
import q.biz.SearchService;
import q.dao.DaoHelper;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Weibo;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang at gmail dot com
 * @date May 14, 2011
 *
 */
public class getAtIndex extends Resource {
	private static long maxId = IdCreator.MAX_ID;

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private FavoriteDao favoriteDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	private CacheService cacheService;

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		this.cacheService.clearAtNotify(loginPeopleId);
		if (context.isApiRequest()) {
			String username = context.getLoginCookie().getUsername();
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId");
			if(startId < maxId) {
				if(startId == 0) { //only first time to visit this page, startId is 0
					startId = IdCreator.MAX_ID;
				}
				int type = context.getInt("type", 0);
				boolean hasPrev = false;
				boolean hasNext = false;
				Map<String, Object> api = new HashMap<String, Object>();
				String searchAft = "";
				if (startId == IdCreator.MAX_ID) {
					searchAft = " " + "AND id:[* TO " + (IdCreator.MAX_ID) + "]";
				} else {
					searchAft = " " + "AND id:[* TO " + (startId - 1) + "]";
				}
				List<Long> bs = searchService.searchWeibo("@" + username + searchAft, size);
				if (CollectionKit.isNotEmpty(bs)) {
					List<Weibo> weibos = weiboDao.getWeibosByIds(bs, true);
					if(startId == IdCreator.MAX_ID && bs.size() > 0) {
						maxId = bs.get(0);
					}
					if (CollectionKit.isNotEmpty(weibos)) {
						DaoHelper.injectWeiboModelsWithQuote(weiboDao, weibos);
						DaoHelper.injectWeiboModelsWithPeople(peopleDao, weibos);
						DaoHelper.injectWeiboModelsWithFrom(groupDao, weibos);
						if (loginPeopleId > 0) {
							DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, weibos, loginPeopleId);
						}
						api.put("weibos", weibos);
					}
				}
				api.put("hasPrev", hasPrev);
				api.put("hasNext", hasNext);
				context.setModel("api", api);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		if (context.getCookiePeopleId() < 0) {
			throw new RequestParameterInvalidException("login:invalid");
		}
	}

}
