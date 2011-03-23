/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	PeopleJoinGroup getGroupPeople(long peopleId, long groupId) throws SQLException;

	void unjoinPeopleJoinGroup(long peopleId, long groupId) throws SQLException;

	void rejoinPeopleJoinGroup(long peopleId, long groupId) throws SQLException;

	public List<Group> getGroupsByIds(List<Long> groupIds) throws SQLException;
	
	public List<Long> getGroupIdsByPeopleId(long peopleId) throws SQLException;
	
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
	
	/**
	 * @param limit
	 * @return
	 * @throws SQLException
	 */
	List<Group> getHotGroups(int limit) throws SQLException;

	/**
	 * @param groupIds
	 * @return
	 */
	Map<Long, String> getGroupIdNameMapByIds(Set<Long> groupIds) throws SQLException;

	/**
	 * @param groupIds
	 * @return
	 */
	List<Long> getExsitGroupIdsByIds(List<Long> groupIds) throws SQLException;

	/**
	 * @return
	 */
	List<Group> getAllGroupsByCatId(long catId) throws SQLException;

	/**
	 * @param groupIds
	 * @param i
	 * @return
	 */
	List<Long> getGroupPeopleIds(List<Long> groupIds, int limit) throws SQLException;

	/**
	 * @param groupId
	 * @param i
	 * @return
	 */
	List<Long> getHotGroupPeopleIds(long groupId, int limit) throws SQLException;

	/**
	 * @param groupId
	 * @param i
	 * @return
	 */
	List<Group> getRecommendGroupsByGroupId(long groupId, int limit) throws SQLException;

	/**
	 * @param groupId
	 * @param limit
	 * @return
	 */
	List<Long> getGroupPeopleIds(long groupId, int limit) throws SQLException;

	/**
	 * @param groupIds
	 * @param limit
	 * @return
	 */
	List<Long> getHotGroupPeopleIds(List<Long> groupIds, int limit) throws SQLException;

}
