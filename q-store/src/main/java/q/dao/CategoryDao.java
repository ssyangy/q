
package q.dao;
import java.sql.SQLException;
import java.util.ArrayList;

import q.domain.Category;
/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
public interface CategoryDao {

	ArrayList<Category> getCategorys()throws  SQLException;
	
	void addCategory(Category p) throws SQLException;

	Category getCategoryById(int cid) throws SQLException;
}
