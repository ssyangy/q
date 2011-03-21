package q.web.people;

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

public class GetPeopleFeed extends Resource {
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
		List<Long> senderIds = peopleDao.getAllFollowingId(loginPeopleId);
		senderIds.add(loginPeopleId);//add login people
		WeiboPage page = new WeiboPage();
		page.setStartIndex(0);
		page.setSize(20);
		page.setSenderIds(senderIds);
		List<Weibo> weibos = weiboDao.getPageFollowingWeibos(page);
		DaoHelper.injectWeibosWithSenderRealName(peopleDao, weibos);
		DaoHelper.injectWeibosWithFrom(groupDao, weibos);
		DaoHelper.injectWeibosWithFavorite(favoriteDao, weibos, loginPeopleId);
		context.setModel("weibos", weibos);
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
