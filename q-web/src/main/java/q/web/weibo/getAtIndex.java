/**
 * 
 */
package q.web.weibo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (context.isApiRequest()) {
			String username = context.getLoginCookie().getUsername();
			List<Long> bs = searchService.searchWeibo("@" + username);
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId", IdCreator.MAX_ID);
			int type = context.getInt("type", 0);
			boolean hasPrev = false;
			boolean hasNext = false;
			Map<String, Object> api = new HashMap<String, Object>();
			if (CollectionKit.isNotEmpty(bs)) {
				List<Weibo> weibos = weiboDao.getWeibosByIds(bs, true);
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
