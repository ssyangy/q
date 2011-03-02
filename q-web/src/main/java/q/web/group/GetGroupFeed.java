package q.web.group;

import java.util.List;

import q.dao.CategoryDao;
import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.WeiboPage;
import q.domain.Group;
import q.domain.Weibo;
import q.util.CollectionKit;
import q.web.Resource;
import q.web.ResourceContext;

public class GetGroupFeed extends Resource {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

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
		long loginPeopleId = context.getLoginPeopleId();
		List<Long> groupIds = this.groupDao.getGroupIdsByPeopleId(loginPeopleId);
		if (CollectionKit.isNotEmpty(groupIds)) {
			WeiboPage page = new WeiboPage();
			page.setStartIndex(0);
			page.setSize(20);
			page.setGroupIds(groupIds);
			List<Weibo> weibos = this.weiboDao.getPageGroupFeedWeibo(page);
			DaoHelper.injectWeibosWithSenderRealNameAndFrom(peopleDao, groupDao, weibos);
			DaoHelper.injectWeibosWithFavorite(favoriteDao, weibos, loginPeopleId);
			context.setModel("weibos", weibos);
			
			List<Group> groups = this.groupDao.getGroupsByIds(groupIds);
			context.setModel("groups", groups);
		}
	}
}
