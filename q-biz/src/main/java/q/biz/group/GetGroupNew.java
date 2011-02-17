package q.biz.group;


import java.sql.SQLException;
import java.util.ArrayList;

import q.dao.CategoryDao;
import q.domain.Category;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author Zhehao
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

        ArrayList<Category>categorys=categoryDao.getCategorys();
        context.setModel("categorys", categorys);

	}
}