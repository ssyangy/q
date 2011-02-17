package q.biz.group;

import java.sql.SQLException;
import java.util.ArrayList;
import q.dao.CategoryDao;
import q.domain.Category;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;
/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
public class GetCategories extends Resource {
	private final static Logger log = Logger.getLogger();

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
