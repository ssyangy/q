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
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Jan 18, 2011
 * 
 */
public class GetPeople extends Resource {
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
		long peopleId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();

		if (!context.isApiRequest()) {
			GetPeopleFrame frame = new GetPeopleFrame();
			frame.setEventDao(eventDao);
			frame.setGroupDao(groupDao);
			frame.setPeopleDao(peopleDao);
			frame.setWeiboDao(weiboDao);
			frame.validate(context);
			frame.execute(context);
		}

		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId");
		WeiboPage page = new WeiboPage();
		page.setSenderId(peopleId);
		page.setSize(size);
		page.setStartIndex(0);
		if (startId > 0) {
			page.setStartId(startId);
		}
		List<Weibo> weibos = weiboDao.getWeibosByPage(page);
		DaoHelper.injectWeiboModelsWithQuote(weiboDao, weibos);
		DaoHelper.injectWeiboModelsWithPeople(peopleDao, weibos);
		DaoHelper.injectWeiboModelsWithFrom(groupDao, weibos);
		if (loginPeopleId > 0) {
			DaoHelper.injectWeiboModelsWithFavorite(favoriteDao, weibos, loginPeopleId);
		}
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
