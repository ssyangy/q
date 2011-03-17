package q.web.category;

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
public class GetCategoryIndex extends Resource {

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		List<Category> categorys = categoryDao.getAllCategorys();
		context.setModel("categorys", categorys);
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
