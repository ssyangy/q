/**
 *
 */
package q.web.weibo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import q.biz.CacheService;
import q.dao.DaoHelper;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.WeiboReplyPage;
import q.domain.People;
import q.domain.WeiboReply;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;

/**
 * @author seanlinwang at gmail dot com
 * @date May 14, 2011
 *
 */
public class GetReplyReceived extends Resource {
	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private FavoriteDao favoriteDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
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
		this.cacheService.clearWeiboReplyNotify(loginPeopleId);

		if (context.isApiRequest()) {
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId", IdCreator.MAX_ID);
			int type = context.getInt("type", 0);
			int asc = 1;
			WeiboReplyPage page = new WeiboReplyPage();
			if (type == asc) { // 1 indicate asc
				page.setDesc(false);
			} else {
				page.setDesc(true);
			}
			boolean hasPrev = false;
			boolean hasNext = false;
			int fetchSize = size + 1;
			page.setSize(fetchSize);
			if (startId > 0) {
				page.setStartId(startId);
			}
			page.setQuoteSenderId(loginPeopleId);
			List<WeiboReply> replies = weiboDao.getWeiboRepliesByPage(page);
			Map<String, Object> api = new HashMap<String, Object>();
			if (CollectionKit.isNotEmpty(replies)) {
				if (replies.size() == fetchSize) {
					if (type == asc) { // more than one previous page
						hasPrev = true;
					} else { // more than one next page
						hasNext = true;
					}
					replies.remove(replies.size() - 1);// remove last one
				}
				if (type == asc) { // this action from next page
					hasNext = true;
				} else if (startId != IdCreator.MAX_ID) {// this action from previous page
					hasPrev = true;
				}
				if (type == asc) { // reverse asc to desc
					WeiboReply[] array = replies.toArray(new WeiboReply[replies.size()]);
					CollectionUtils.reverseArray(array);
					replies = Arrays.asList(array);
				}
				DaoHelper.injectWeiboModelsWithQuote(weiboDao, replies);
				DaoHelper.injectWeiboModelsWithPeople(peopleDao, replies);
				DaoHelper.injectWeiboModelsWithFrom(groupDao, replies);
				DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, replies, loginPeopleId);
				api.put("replies", replies);
			}
			api.put("hasPrev", hasPrev);
			api.put("hasNext", hasNext);
			context.setModel("api", api);
		} else {
			People people = this.peopleDao.getPeopleById(loginPeopleId);
			context.setModel("people", people);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
	}

}
