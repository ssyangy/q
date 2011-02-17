/**
 * 
 */
package q.biz.group;
import java.sql.SQLException;
import java.util.Date;

import q.dao.CategoryDao;
import q.domain.Category;
import q.web.Resource;
import q.web.ResourceContext;
/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
public class AddCategory extends Resource {

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
@Override
public void execute(ResourceContext context) throws SQLException{
     Category category=new Category();
     category.setId(System.currentTimeMillis());
     category.setName(context.getString("qkname"));
     category.setIntro(context.getString("qkinformation"));
     category.setStatus("0");
     category.setCreated(new Date());
     categoryDao.addCategory(category);
}
}
