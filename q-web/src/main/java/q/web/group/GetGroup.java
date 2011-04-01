/**
 * 
 */
package q.web.group;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.WeiboPage;
import q.domain.Weibo;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 16, 2011
 * 
 */
public class GetGroup extends Resource {
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

	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	private FavoriteDao favoriteDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long groupId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId");
		String tab = context.getString("tab");

		WeiboPage page = new WeiboPage();
		page.setGroupId(groupId);
		page.setSize(size);
		page.setStartIndex(0);
		if (startId > 0) {
			page.setStartId(startId);
		}
		if ("created".equals(tab)) {
			page.setSenderId(loginPeopleId);
		}
		List<Long> weiboIds = weiboDao.getWeiboIdsByPage(page);
		List<Weibo> weibos = weiboDao.getWeibosByIds(weiboIds, true);
		DaoHelper.injectWeiboModelsWithQuote(weiboDao, weibos);
		DaoHelper.injectWeiboModelsWithPeople(peopleDao, weibos);
		DaoHelper.injectWeiboModelsWithFrom(groupDao, weibos);
		if (loginPeopleId > 0) {
			DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, weibos, loginPeopleId);
		}
		context.setModel("weibos", weibos);

		if (!context.isApiRequest()) {
			GetGroupFrame frame = new GetGroupFrame();
			frame.setEventDao(eventDao);
			frame.setGroupDao(groupDao);
			frame.setPeopleDao(peopleDao);
			frame.setWeiboDao(weiboDao);
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
