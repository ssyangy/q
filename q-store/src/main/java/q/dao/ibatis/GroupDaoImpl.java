/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;

import q.dao.GroupDao;

import q.domain.Group;
import q.domain.GroupJoinCategory;
import q.domain.People;
import q.domain.PeopleGroupJoin;

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
		this.sqlMapClient.insert("insertGroupCategory", gc);

	}

	@Override
	public void addPeopleJoinGroup(PeopleGroupJoin peopleGroupJoin) throws SQLException {
		this.sqlMapClient.insert("insertPeopleJoinGroup", peopleGroupJoin);
	}

	@Override
	public PeopleGroupJoin whetherJoined(PeopleGroupJoin peopleGroupJoin) throws SQLException {
		// TODO Auto-generated method stub
		return (PeopleGroupJoin) this.sqlMapClient.queryForObject("whetherjoined", peopleGroupJoin);
	}

}
