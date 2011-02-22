package q.web.group;

import java.sql.SQLException;
import java.util.List;

import q.dao.CategoryDao;
import q.domain.Category;
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

	@Override
	public void execute(ResourceContext context) throws SQLException {
		List<Category> categorys = categoryDao.getCategorys();
		context.setModel("categorys", categorys);

	}
}