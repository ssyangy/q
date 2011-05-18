/**
 *
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import q.dao.GroupDao;
import q.dao.page.GroupJoinCategoryPage;
import q.dao.page.GroupPage;
import q.dao.page.GroupRecommendPage;
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
	public void addGroup(Group group) throws SQLException {
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
	public Group getGroupById(long gid) throws SQLException {
		return (Group) this.sqlMapClient.queryForObject("selectGroupById", gid);
	}

	@Override
	public PeopleJoinGroup getJoinPeopleByGroupIdPeopleId(long peopleId, long groupId) throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setGroupId(groupId);
		page.setPeopleId(peopleId);
		return (PeopleJoinGroup) this.sqlMapClient.queryForObject("selectPeopleJoinGroupByPage", page);
	}

	@Override
	public List<Long> getJoinPeopleIdsByGroupId(long groupId, int limit, int start) throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setSize(limit);
		page.setStartIndex(start);
		page.setGroupId(groupId);
		return getJoinPeopleIdsByJoinPage(page);
	}

	@Override
	public List<Long> getJoinPeopleIdsByGroupIds(List<Long> groupIds, int limit, int start) throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setSize(limit);
		page.setStartIndex(start);
		page.setGroupIds(groupIds);
		return getJoinPeopleIdsByJoinPage(page);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> getJoinPeopleIdsByJoinPage(PeopleJoinGroupPage page) throws SQLException {
		return (List<Long>) this.sqlMapClient.queryForList("selectJoinPeopleIdsByPage", page);
	}

	@Override
	public List<Group> getGroupsByJoinPeopleId(long peopleId) throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setPeopleId(peopleId);
		List<Long> groupIds = this.getGroupIdsByJoinPage(page);
		List<Group> groups = null;
		if (CollectionKit.isNotEmpty(groupIds)) {
			groups = this.getGroupsByIds(groupIds);
		}
		return groups;
	}

	@Override
	public List<Long> getGroupIdsByJoinPeopleId(long peopleId) throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setPeopleId(peopleId);
		return this.getGroupIdsByJoinPage(page);
	}

	@Override
	public List<Long> getGroupIdsByJoinPeopleIdAndGroupIds(long peopleId, List<Long> groupIds) throws SQLException {
		PeopleJoinGroupPage page = new PeopleJoinGroupPage();
		page.setPeopleId(peopleId);
		page.setGroupIds(groupIds);
		return this.getGroupIdsByJoinPage(page);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Long> getGroupIdsByJoinPage(PeopleJoinGroupPage page) throws SQLException {
		return this.sqlMapClient.queryForList("selectJoinGroupIdsByPage", page);
	}

	@Override
	public List<Long> getJoinPeopleIdsByHotAndGroupId(long groupId, int limit, int start) throws SQLException {
		return this.getJoinPeopleIdsByGroupId(groupId, limit, start);
	}

	@Override
	public List<Long> getJoinPeopleIdsByHotAndGroupIds(List<Long> groupIds, int limit, int start) throws SQLException {
		return this.getJoinPeopleIdsByGroupIds(groupIds, limit, start);
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

	@Override
	public List<Group> getGroupsByLocation(Group myLocation) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Group> groups = this.sqlMapClient.queryForList("selectGroupsByLocation", myLocation);
		return groups;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see q.dao.CategoryDao#getNewGroups(int)
	 */
	@Override
	public List<Group> getNewGroups(int limit) throws SQLException {
		GroupPage page = new GroupPage();
		page.setSize(limit);
		return (List<Group>) this.getGroupsByPage(page);
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
	 * @see q.dao.GroupDao#getAllPromotedGroups(java.util.List)
	 */
	@Override
	public List<Group> getAllPromotedGroups(List<Long> categoryIds) throws SQLException {
		GroupJoinCategoryPage page = new GroupJoinCategoryPage();
		page.setCategoryIds(categoryIds);
		@SuppressWarnings("unchecked")
		List<GroupJoinCategory> joins = this.sqlMapClient.queryForList("getPromotedGroupJoinCategoriesByCatIds", page);
		if (CollectionKit.isEmpty(joins)) {
			return null;
		}
		Map<Long, GroupJoinCategory> groupId2CatMap = new HashMap<Long, GroupJoinCategory>(joins.size());
		for (GroupJoinCategory join : joins) {
			groupId2CatMap.put(join.getGroupId(), join);
		}
		Set<Long> groupIdSet = groupId2CatMap.keySet();
		List<Group> groups = this.getGroupsByIds(new ArrayList<Long>(groupIdSet));
		for (Group group : groups) {
			GroupJoinCategory join = groupId2CatMap.get(group.getId());
			if (join != null) {
				group.setCategoryId(join.getCategoryId());
			}
		}
		return groups;
	}

	public List<Group> getGroupsByPage(GroupPage page) throws SQLException {
		@SuppressWarnings("unchecked")
		List<Group> groups = this.sqlMapClient.queryForList("getGroupsByPage", page);
		return groups;
	}

	@Override
	public List<Group> getRecommendGroupsByPage(GroupRecommendPage page) throws SQLException {
		// FIXME sean, use new groups instead
		GroupPage gpage = new GroupPage();
		gpage.setSize(6);
		return this.getGroupsByPage(gpage);
	}
}
