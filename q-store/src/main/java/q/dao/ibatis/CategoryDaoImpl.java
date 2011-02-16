package q.dao.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category getCategoryById(int cid) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Category> getCategorys() throws SQLException {
		return  (ArrayList<Category>)this.sqlMapClient.queryForList("selectCategorys");
	}



}
