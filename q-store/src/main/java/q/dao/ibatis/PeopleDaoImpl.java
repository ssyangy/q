/**
 *
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import q.dao.PeopleDao;
import q.dao.page.PeoplePage;
import q.dao.page.PeopleRelationPage;
import q.domain.Area;
import q.domain.People;
import q.domain.PeopleRelation;
import q.domain.PeopleRelationStatus;
import q.util.CollectionKit;
import q.util.IdCreator;

/**
 * @author alin
 * @date Feb 10, 2011
 * 
 */
public class PeopleDaoImpl extends AbstractDaoImpl implements PeopleDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeoplesDao#addPeople(q.domain.People)
	 */
	@Override
	public void addPeople(People p) throws SQLException {
		p.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertPeople", p);
	}

	@Override
	public void updatePeopleById(People p) throws SQLException {
		this.sqlMapClient.update("updatePeopleById", p);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeoplesDao#getPeopleById(int)
	 */
	@Override
	public People getPeopleById(long pid) throws SQLException {
		PeoplePage page = new PeoplePage();
		page.setId(pid);
		return getPeopleByPage(page);
	}

	@Override
	public People getPeopleByEmail(String email) throws SQLException {
		PeoplePage page = new PeoplePage();
		page.setEmail(email);
		return getPeopleByPage(page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#getPeopleByUsername(java.lang.String)
	 */
	@Override
	public People getPeopleByUsername(String username) throws SQLException {
		PeoplePage page = new PeoplePage();
		page.setUsername(username);
		return getPeopleByPage(page);
	}

	@Override
	public List<People> getHotPeoplesByArea(Area area, int limit) throws SQLException {
		PeoplePage page = new PeoplePage();
		page.setArea(area);
		page.setSize(limit);
		page.setStartIndex(0);
		return this.getPeoplesByPage(page);
	}

	@Override
	public List<People> getPeoplesByIds(List<Long> ids) throws SQLException {
		if (CollectionKit.isEmpty(ids)) {
			return null;
		}
		PeoplePage page = new PeoplePage();
		page.setIds(ids);
		return this.getPeoplesByPage(page);
	}

	private People getPeopleByPage(PeoplePage page) throws SQLException {
		return (People) this.sqlMapClient.queryForObject("selectPeoplesByPage", page);
	}

	@SuppressWarnings("unchecked")
	private List<People> getPeoplesByPage(PeoplePage page) throws SQLException {
		return (List<People>) this.sqlMapClient.queryForList("selectPeoplesByPage", page);
	}

	@Override
	public Map<Long, String> getIdRealNameMapByIds(Set<Long> peopleIds) throws SQLException {
		@SuppressWarnings("unchecked")
		List<People> peoples = (List<People>) this.sqlMapClient.queryForList("selectPeopleIdRealNamesByIds", peopleIds.toArray());
		if (CollectionKit.isEmpty(peoples)) {
			return null;
		}
		Map<Long, String> map = new HashMap<Long, String>(peoples.size());
		for (People p : peoples) {
			map.put(p.getId(), p.getRealName());
		}
		return map;
	}

	@Override
	public void addPeopleRelation(PeopleRelation relation) throws SQLException {
		relation.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertPeopleRelation", relation);
	}

	@Override
	public List<PeopleRelation> getPeopleRelationsByPage(PeopleRelationPage page) throws SQLException {
		@SuppressWarnings("unchecked")
		List<PeopleRelation> relations = (List<PeopleRelation>) this.sqlMapClient.queryForList("selectPeopleRelationsByPage", page);
		return relations;
	}

	@Override
	public List<Long> getAllFollowingId(long fromPeopleId) throws SQLException {
		PeopleRelation query = new PeopleRelation();
		query.setStatus(PeopleRelationStatus.FOLLOWING);
		query.setFromPeopleId(fromPeopleId);
		@SuppressWarnings("unchecked")
		List<Long> followingIds = this.sqlMapClient.queryForList("selectAllToPeopleIds", query);
		return followingIds;
	}

	@Override
	public PeopleRelation getPeopleRelationByFromIdToId(long fromPeopleId, long toPeopleId) throws SQLException {
		return (PeopleRelation) this.getSqlMapClient().queryForObject("selectPeopleRelationByFromIdToId", new PeopleRelation(fromPeopleId, toPeopleId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#updatePeopleRelationStatus(q.domain.PeopleRelation)
	 */
	@Override
	public int updatePeopleRelationStatusById(PeopleRelationStatus newStatus, PeopleRelationStatus oldStatus, long relationId) throws SQLException {
		PeopleRelation relation = new PeopleRelation();
		relation.setId(relationId);
		relation.setStatus(newStatus);
		relation.setOldStatus(oldStatus);
		return this.sqlMapClient.update("updatePeopleRelationStatusById", relation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#updatePeopleRelationStatusByFromIdAndToId(q.domain.PeopleRelationStatus, long, long)
	 */
	@Override
	public int updatePeopleRelationStatusByFromIdAndToId(PeopleRelationStatus newStatus, PeopleRelationStatus oldStatus, long fromPeopleId, long toPeopleId) throws SQLException {
		PeopleRelation relation = new PeopleRelation();
		relation.setFromPeopleId(fromPeopleId);
		relation.setToPeopleId(toPeopleId);
		relation.setStatus(newStatus);
		relation.setOldStatus(oldStatus);
		return this.sqlMapClient.update("updatePeopleRelationStatusByFromIdToId", relation);
	}

	@Override
	public int getPeopleFollowingNumById(long peopleId) throws SQLException {
		return (Integer) this.sqlMapClient.queryForObject("selectPeopleFollowingNumById", peopleId);
	}

	@Override
	public int getPeopleFollowerNumById(long peopleId) throws SQLException {
		return (Integer) this.sqlMapClient.queryForObject("selectPeopleFollowerNumById", peopleId);
	}

	@Override
	public People getInterestById(long pid) throws SQLException {
		return (People) this.sqlMapClient.queryForObject("selectPeopleInterestsById", pid);
	}

	@Override
	public void updateInterestById(People p) throws SQLException {
		this.sqlMapClient.update("updatePeopleInterestsById", p);
	}

	@Override
	public String selectPasswordById(long pid) throws SQLException {
		return (String) this.sqlMapClient.queryForObject("selectPasswordById", pid);
	}

	@Override
	public int decrPeopleWeiboNumberByPeopleId(long senderId) throws SQLException {
		return this.sqlMapClient.update("decrPeopleWeiboNumberByPeopleId", senderId);
	}

	@Override
	public int incrPeopleWeiboNumberByPeopleId(long senderId) throws SQLException {
		return this.sqlMapClient.update("incrPeopleWeiboNumberByPeopleId", senderId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#incrPeopleFollowingNumberByPeopleId(long)
	 */
	@Override
	public int incrPeopleFollowingNumberByPeopleId(long peopleId) throws SQLException {
		return this.sqlMapClient.update("incrPeopleFollowingNumberByPeopleId", peopleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#incrPeopleFollowerNumberByPeopleId(long)
	 */
	@Override
	public int incrPeopleFollowerNumberByPeopleId(long peopleId) throws SQLException {
		return this.sqlMapClient.update("incrPeopleFollowerNumberByPeopleId", peopleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#decrPeopleFollowingNumberByPeopleId(long)
	 */
	@Override
	public int decrPeopleFollowingNumberByPeopleId(long peopleId) throws SQLException {
		return this.sqlMapClient.update("decrPeopleFollowingNumberByPeopleId", peopleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#decrPeopleFollowerNumberByPeopleId(long)
	 */
	@Override
	public int decrPeopleFollowerNumberByPeopleId(long peopleId) throws SQLException {
		return this.sqlMapClient.update("decrPeopleFollowerNumberByPeopleId", peopleId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#updatePassword(java.lang.String)
	 */
	@Override
	public int updatePasswordByPeopleId(long peopleId, String newPassword) throws SQLException {
		People p = new People();
		p.setId(peopleId);
		p.setPassword(newPassword);
		return this.sqlMapClient.update("updatePasswordByPeopleId", p);
	}

}
