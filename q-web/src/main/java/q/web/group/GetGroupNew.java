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
public class GetGroupNew extends Resource {

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
		List<Category> categorys = categoryDao.getAllCategorys();
		context.setModel("categorys", categorys);
		long loginId = context.getCookiePeopleId();
		if (loginId > 0) {
			List<Group> groups = groupDao.getGroupsByJoinPeopleId(loginId);
			context.setModel("groups", groups);
		}

	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}
}