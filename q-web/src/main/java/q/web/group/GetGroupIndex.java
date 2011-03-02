package q.web.group;

import java.sql.SQLException;
import java.util.List;

import q.dao.CategoryDao;
import q.dao.GroupDao;
import q.domain.Category;
import q.domain.Group;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author Zhehao
 * @author seanlinwang
 * @date Feb 15, 2011
 * 
 */
public class GetGroupIndex extends Resource {

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		List<Category> categorys = categoryDao.getCategorys();
		context.setModel("categorys", categorys);
		List<Group> groups = groupDao.getNewGroups(10);
		context.setModel("newGroups", groups);
	}

}
