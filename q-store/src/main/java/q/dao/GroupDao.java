/**
 *
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import q.dao.page.GroupRecommendPage;
import q.dao.page.PeopleJoinGroupPage;
import q.domain.Group;
import q.domain.GroupJoinCategory;
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

	PeopleJoinGroup getJoinPeopleByGroupIdPeopleId(long peopleId, long groupId) throws SQLException;

	void unjoinPeopleJoinGroup(long peopleId, long groupId) throws SQLException;

	void rejoinPeopleJoinGroup(long peopleId, long groupId) throws SQLException;

	public List<Group> getGroupsByIds(List<Long> groupIds) throws SQLException;

	public List<Long> getGroupIdsByJoinPage(PeopleJoinGroupPage page) throws SQLException;

	public List<Group> getGroupsByLocation(Group myLocation) throws SQLException;

	/**
	 * @param peopleId
	 * @throws SQLException
	 */
	List<Group> getGroupsByJoinPeopleId(long peopleId) throws SQLException;

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
	 * @param limit
	 * @param start
	 * @return
	 */
	List<Long> getJoinPeopleIdsByGroupIds(List<Long> groupIds, int limit, int start) throws SQLException;

	/**
	 * @param groupId
	 * @param limit
	 * @param start
	 * @return
	 */
	List<Long> getJoinPeopleIdsByHotAndGroupId(long groupId, int limit, int start) throws SQLException;

	/**
	 * @param groupId
	 * @param limit
	 * @param start
	 * @return
	 */
	List<Long> getJoinPeopleIdsByGroupId(long groupId, int limit, int start) throws SQLException;

	/**
	 * @param groupIds
	 * @param limit
	 * @param start
	 * @return
	 */
	List<Long> getJoinPeopleIdsByHotAndGroupIds(List<Long> groupIds, int limit, int start) throws SQLException;

	/**
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	List<Long> getJoinPeopleIdsByJoinPage(PeopleJoinGroupPage page) throws SQLException;

	/**
	 * @return
	 * @throws SQLException
	 */
	List<Group> getAllGroups() throws SQLException;

	/**
	 * @param peopleId
	 * @return
	 * @throws SQLException
	 */
	List<Long> getGroupIdsByJoinPeopleId(long peopleId) throws SQLException;

	/**
	 * @param peopleId
	 * @param groupIds
	 * @return
	 * @throws SQLException
	 */
	List<Long> getGroupIdsByJoinPeopleIdAndGroupIds(long peopleId, List<Long> groupIds) throws SQLException;

	/**
	 * @param catIds
	 * @return
	 */
	List<Group> getAllPromotedGroups(List<Long> catIds) throws SQLException;

	/**
	 * @param page
	 * @return
	 */
	List<Group> getRecommendGroupsByPage(GroupRecommendPage page) throws SQLException;

	/**
	 * @return
	 */
	List<GroupJoinCategory> getGroupJoinCategoriesByGroupId(long groupId) throws SQLException;

	/**
	 * @param groupId
	 * @param ids
	 */
	int deleteGroupJoinCategoriesByjoinIdsAndGroupId(long groupId, List<Long> ids) throws SQLException;

	/**
	 * @param group
	 */
	int  updateGroup(Group group) throws SQLException;

}
