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
import q.util.CollectionKit;
import q.web.Resource;
import q.web.ResourceContext;

public class GetGroupFeed extends Resource {

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
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

	private FavoriteDao favoriteDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		List<Long> groupIds = this.groupDao.getGroupIdsByPeopleId(loginPeopleId);
		String tab = context.getString("tab");

		if (CollectionKit.isNotEmpty(groupIds)) {
			WeiboPage page = new WeiboPage();
			page.setStartIndex(0);
			page.setSize(20);
			page.setGroupIds(groupIds);
			if ("created".equals(tab)) {
				page.setSenderId(loginPeopleId);
			}
			List<Weibo> weibos = this.weiboDao.getWeibosByPage(page);
			DaoHelper.injectWeiboModelsWithSenderRealName(peopleDao, weibos);
			DaoHelper.injectWeiboModelsWithFrom(groupDao, weibos);
			DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, weibos, loginPeopleId);
			context.setModel("weibos", weibos);

			GetGroupFeedFrame frame = new GetGroupFeedFrame();
			frame.setEventDao(eventDao);
			frame.setPeopleDao(peopleDao);
			frame.setGroupDao(groupDao);
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
		// TODO Auto-generated method stub

	}
}
