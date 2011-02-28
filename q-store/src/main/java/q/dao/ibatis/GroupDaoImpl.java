/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import q.dao.GroupDao;
import q.domain.Group;
import q.domain.GroupJoinCategory;
import q.domain.PeopleJoinGroup;
import q.domain.Status;
import q.util.CollectionKit;
import q.util.IdCreator;

/**
 * @author Zhehao
 * @author seanlinwang
 * @date Feb 15, 2011
 * 
 */

public class GroupDaoImpl extends AbstractDaoImpl implements GroupDao {
	@Override
	public Group getGroupById(long gid) throws SQLException {
		return (Group) this.sqlMapClient.queryForObject("selectGroupById", gid);
	}

	@Override
	public void addGroup(Group group) throws SQLException {
		group.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertGroup", group);

	}

	@Override
	public void addGroupJoinCategory(long groupId, long categoryId) throws SQLException {
		GroupJoinCategory join = new GroupJoinCategory();
		join.setCategoryId(categoryId);
		join.setGroupId(groupId);

		join.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertGroupJoinCategory", join);
	}

	@Override
	public void addPeopleJoinGroup(long peopleId, long groupId) throws SQLException {
		PeopleJoinGroup join = new PeopleJoinGroup();
		join.setPeopleId(peopleId);
		join.setGroupId(groupId);

		join.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertPeopleJoinGroup", join);
	}

	@Override
	public PeopleJoinGroup getPeopleJoinGroup(long peopleId, long groupId) throws SQLException {
		return (PeopleJoinGroup) this.sqlMapClient.queryForObject("selectPeopleJoinGroupByPeopleIdAndGroupId", new PeopleJoinGroup(peopleId, groupId));
	}

	@Override
	public void unjoinPeopleJoinGroup(long peopleId, long groupId) throws SQLException {
		PeopleJoinGroup join = new PeopleJoinGroup();
		join.setPeopleId(peopleId);
		join.setGroupId(groupId);
		join.setStatus(Status.DELETE.getValue());
		this.sqlMapClient.update("updatePeopleJoinGroupStatusByPeopleIdAndGroupId", join);
	}

	@Override
	public void rejoinPeopleJoinGroup(long peopleId, long groupId) throws SQLException {
		PeopleJoinGroup join = new PeopleJoinGroup();
		join.setPeopleId(peopleId);
		join.setGroupId(groupId);
		join.setStatus(Status.COMMON.getValue());
		this.sqlMapClient.update("updatePeopleJoinGroupStatusByPeopleIdAndGroupId", join);
	}

	@Override
	public List<Group> getGroupsByPeopleId(long peopleId) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Group> groups = this.sqlMapClient.queryForList("selectGroupsByPeopleId", peopleId);
		return groups;
	}

	public List<Group> getGroupsByGroupIds(List<Long> groupIds) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Group> groups = this.sqlMapClient.queryForList("selectGroupsByGroupIds", groupIds);
		return groups;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.CategoryDao#getNewGroups(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Group> getNewGroups(int i) throws SQLException {
		return (List<Group>) this.sqlMapClient.queryForList("selectNewGroups", i);
	}

	@Override
	public Map<Long, String> getGroupIdNameMapByIds(Set<Long> groupIds) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Group> groups = (List<Group>)this.sqlMapClient.queryForList("selectIdNamesByIds", groupIds.toArray());
		if (CollectionKit.isEmpty(groups)) {
			return null;
		}
		Map<Long, String> IdNameMap = new HashMap<Long, String>(groups.size());
		for (Group group : groups) {
			IdNameMap.put(group.getId(), group.getName());
		}
		return IdNameMap;
	}

}
