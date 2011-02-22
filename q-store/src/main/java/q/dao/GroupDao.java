/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;

import q.domain.Group;
import q.domain.PeopleJoinGroup;

/**
 * @author Zhehao
 * @date Feb 15, 2011
 * 
 */
public interface GroupDao {

	void addGroup(Group p) throws SQLException;

	Group getGroupById(long gid) throws SQLException;

	void addGroupJoinCategory(long groupId, long categoryId) throws SQLException;

	void addPeopleJoinGroup(long peopleId, long groupId) throws SQLException;

	PeopleJoinGroup getPeopleJoinGroup(long peopleId, long groupId) throws SQLException;

	void unjoinPeopleJoinGroup(long peopleId, long groupId) throws SQLException;

	void rejoinPeopleJoinGroup(long peopleId, long groupId) throws SQLException;

	/**
	 * @param peopleId
	 * @throws SQLException
	 */
	List<Group> getGroupsByPeopleId(long peopleId) throws SQLException;

	/**
	 * @param limit
	 * @return
	 */
	List<Group> getNewGroups(int limit) throws SQLException;
}
