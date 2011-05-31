package q.dao;

import java.sql.SQLException;
import java.util.List;

import q.domain.Category;

/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
public interface CategoryDao {

	List<Category> getAllCategorys() throws SQLException;

	void addCategory(Category p) throws SQLException;

	Category getCategoryById(long cid) throws SQLException;

	void updateCategory(Category category) throws SQLException;

	void deleteCategory(Category category) throws SQLException;

}
