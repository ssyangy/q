/**
 *
 */
package q.web.weibo;

import java.util.Map;

import q.biz.CacheService;
import q.biz.WeiboService;
import q.dao.PeopleDao;
import q.domain.People;
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

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private CacheService cacheService;

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	private WeiboService weiboService;

	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
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
			Map<String, Object> api = weiboService.getReplyReceivedPagination(loginPeopleId, size, startId, type);
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
