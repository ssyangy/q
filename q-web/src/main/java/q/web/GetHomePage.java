package q.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import q.dao.CategoryDao;
import q.dao.DaoHelper;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Area;
import q.domain.Category;
import q.util.CollectionKit;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 * 
 */
public class GetHomePage extends Resource {
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

	@Override
	public void execute(ResourceContext context) throws IOException, SQLException {
		if (context.isLogin()) {
			context.redirectServletPath("/category");
			return;
		}
		int areaId = 310104;// FIXME 徐汇区 wanglin
		Area area = Area.getAreaById(areaId);
		context.setModel("city", area);
		context.setModel("province", area.getParent());
		List<Category> categories = this.categoryDao.getAllCategorys();
		context.setModel("cats", categories);
		if (CollectionKit.isNotEmpty(categories)) {
			DaoHelper.injectCategoriesWithPromotedGroups(groupDao, categories);
		}
		// List<Event> hotEvents = this.eventDao.getHotEvents(10);
		// context.setModel("hotEvents", hotEvents);
		// List<People> hotLocals = this.peopleDao.getHotPeoplesByArea(area, 5);
		// context.setModel("hotLocals", hotLocals);
		List<q.domain.Weibo> hotWeibos = this.weiboDao.getHotWeibos(5);
		DaoHelper.injectWeiboModelsWithPeople(peopleDao, hotWeibos);
		context.setModel("hotWeibos", hotWeibos);
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
