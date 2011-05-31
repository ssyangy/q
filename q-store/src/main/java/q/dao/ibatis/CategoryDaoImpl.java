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
public class CategoryDaoImpl extends AbstractDaoImpl implements CategoryDao {

	@Override
	public void addCategory(Category p) throws SQLException {
		this.sqlMapClient.insert("insertCategory", p);
	}

	@Override
	public Category getCategoryById(long cid) throws SQLException {
		return (Category)this.sqlMapClient.queryForObject("selectCategoryById", cid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategorys() throws SQLException {
		return (List<Category>) this.sqlMapClient.queryForList("selectCategorys");
	}

	@Override
	public void updateCategory(Category category) throws SQLException {
		this.sqlMapClient.update("updateCategoryById", category);
	}

	@Override
	public void deleteCategory(Category category) throws SQLException {
		this.sqlMapClient.delete("deleteCategoryById", category);
	}

}
