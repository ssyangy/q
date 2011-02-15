/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;

import q.dao.GroupDao;

import q.domain.Group;
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

	@Override
	public Group getGroupById(int gid) throws SQLException {
		return (Group) this.sqlMapClient.queryForObject("selectGroupById", gid);
	}



}
