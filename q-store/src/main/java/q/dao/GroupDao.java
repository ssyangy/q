/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import q.domain.Group;
import q.domain.GroupJoinCategory;
import q.domain.People;
import q.domain.PeopleGroupJoin;

/**
 * @author Zhehao
 * @date Feb 15, 2011
 * 
 */
public interface GroupDao {

	void addGroup(Group p) throws SQLException;

	Group getGroupById(long gid) throws SQLException;

	void addGroupCategory(GroupJoinCategory gc) throws SQLException;

	void addPeopleJoinGroup(PeopleGroupJoin peopleGroupJoin) throws SQLException;
	
	PeopleGroupJoin whetherJoined(PeopleGroupJoin peopleGroupJoin) throws SQLException;
}
