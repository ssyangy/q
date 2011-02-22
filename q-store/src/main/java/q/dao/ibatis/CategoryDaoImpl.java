package q.dao.ibatis;

import java.sql.SQLException;
import java.util.List;

import q.dao.CategoryDao;
import q.domain.Category;
/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
public class CategoryDaoImpl extends AbstractDaoImpl implements CategoryDao{

	@Override
	public void addCategory(Category p) throws SQLException {
		this.sqlMapClient.insert("insertCategory", p);
		
	}

	@Override
	public Category getCategoryById(int cid) throws SQLException {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategorys() throws SQLException {
		return  (List<Category>)this.sqlMapClient.queryForList("selectCategorys");
	}

}
