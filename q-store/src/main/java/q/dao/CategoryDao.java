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

	List<Category> getCategorys() throws SQLException;

	void addCategory(Category p) throws SQLException;

	Category getCategoryById(int cid) throws SQLException;

}
