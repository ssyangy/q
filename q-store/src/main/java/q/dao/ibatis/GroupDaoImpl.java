/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;

import q.dao.GroupDao;

import q.domain.Group;
import q.domain.GroupJoinCategory;
/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */

public class GroupDaoImpl extends AbstractDaoImpl implements GroupDao {

	@Override
	public void addGroup(Group g) throws SQLException {
		this.sqlMapClient.insert("insertGroup", g);
		
	}


	public Group getGroupById(long gid) throws SQLException {
		return (Group) this.sqlMapClient.queryForObject("selectGroupById", gid);
	}


	@Override
	public void addGroupCategory(GroupJoinCategory gc) throws SQLException {
		this.sqlMapClient.insert("insertGroupCategory",gc);
		
	}



}
