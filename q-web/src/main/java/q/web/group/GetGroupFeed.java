package q.web.group;

import java.util.List;

import q.dao.CategoryDao;
import q.dao.GroupDao;
import q.domain.Group;
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

	@Override
	public void execute(ResourceContext context) throws Exception {
		List<Group> groups = this.groupDao.getGroupsByPeopleId(context.getLoginPeopleId());
		context.setModel("groups", groups);
	}

}
