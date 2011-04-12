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
import q.dao.page.PeopleJoinGroupPage;
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
		join.setStatus(Status.COMMON.getValue());
		join.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertPeopleJoinGroup", join);
	}

	@Override
	public PeopleJoinGroup getGroupPeople(long peopleId, long groupId) throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setGroupId(groupId);
		page.setPeopleId(peopleId);
		return (PeopleJoinGroup) this.sqlMapClient.queryForObject("selectPeopleJoinGroupByPage", page);
	}

	@Override
	public List<Long> getPeopleIdsByGroupId(long groupId, int limit, int start) throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setSize(limit);
		page.setStartIndex(start);
		page.setGroupId(groupId);
		return getPeopleIdsByPage(page);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getPeopleIdsByPage(PeopleJoinGroupPage page) throws SQLException {
		return (List<Long>) this.sqlMapClient.queryForList("selectJoinPeopleIdsByPage", page);
	}

	@Override
	public List<Long> getPeopleIdsByGroupIds(List<Long> groupIds, int limit, int start) throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setSize(limit);
		page.setStartIndex(start);
		page.setGroupIds(groupIds);
		return getPeopleIdsByPage(page);
	}

	@Override
	public List<Group> getGroupsByPeopleId(long peopleId) throws SQLException {
		List<Long> groupIds = this.getGroupIdsByPeopleId(peopleId);
		if (CollectionKit.isEmpty(groupIds)) {
			return null;
		}
		List<Group> groups = this.getGroupsByIds(groupIds);
		return groups;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Long> getGroupIdsByPeopleId(long peopleId) throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setPeopleId(peopleId);
		return this.sqlMapClient.queryForList("selectJoinGroupIdsByPage", page);
	}

	@Override
	public List<Long> getHotGroupPeopleIds(long groupId, int limit, int start) throws SQLException {
		return this.getPeopleIdsByGroupId(groupId, limit, start);
	}

	@Override
	public List<Long> getHotGroupPeopleIds(List<Long> groupIds, int limit, int start) throws SQLException {
		return this.getPeopleIdsByGroupIds(groupIds, limit, start);
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
	public List<Group> getAllGroupsByCatId(long catId) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Long> groupIds = this.sqlMapClient.queryForList("selectGroupIdsByCatId", catId);
		List<Group> groups = this.getGroupsByIds(groupIds);
		return groups;
	}
	
	@Override
	public List<Group> getAllGroups() throws SQLException {
		@SuppressWarnings("unchecked")
		List<Group> groups = this.sqlMapClient.queryForList("selectAllGroups");
		return groups;
	}

	@Override
	public List<Long> getExsitGroupIdsByIds(List<Long> groupIds) throws SQLException {
		if (CollectionKit.isEmpty(groupIds)) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<Long> ids = this.sqlMapClient.queryForList("selectGroupIdsByIds", groupIds);
		return ids;
	}

	@Override
	public List<Group> getGroupsByIds(List<Long> groupIds) throws SQLException {
		if (CollectionKit.isEmpty(groupIds)) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<Group> groups = this.sqlMapClient.queryForList("selectGroupsByIds", groupIds);
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
	public List<Group> getHotGroups(int limit) throws SQLException {
		return this.getNewGroups(limit);
	}

	@Override
	public Map<Long, String> getGroupIdNameMapByIds(Set<Long> groupIds) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Group> groups = (List<Group>) this.sqlMapClient.queryForList("selectIdNamesByIds", groupIds.toArray());
		if (CollectionKit.isEmpty(groups)) {
			return null;
		}
		Map<Long, String> IdNameMap = new HashMap<Long, String>(groups.size());
		for (Group group : groups) {
			IdNameMap.put(group.getId(), group.getName());
		}
		return IdNameMap;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.GroupDao#getRecommendGroupsByGroupId(long, int)
	 */
	@Override
	public List<Group> getRecommendGroupsByGroupId(long groupId, int limit, int start) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
