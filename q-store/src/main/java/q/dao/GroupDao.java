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
	 * @param loginPeopleId
	 * @return TODO
	 * @throws SQLException 
	 */
	List<Group> getGroupsByPeopleId(long loginPeopleId) throws SQLException;
}
