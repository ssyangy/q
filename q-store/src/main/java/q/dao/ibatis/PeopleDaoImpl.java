/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import q.dao.PeopleDao;
import q.dao.page.PeopleRelationPage;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeoplesDao#getPeopleById(int)
	 */
	@Override
	public People getPeopleById(long pid) throws SQLException {
		return (People) this.sqlMapClient.queryForObject("selectPeopleById", pid);
	}

	@Override
	public void updatePeople(People p) throws SQLException {
		this.sqlMapClient.update("updatePeopleById", p);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#getPeopleByUsername(java.lang.String)
	 */
	@Override
	public People getPeopleByUsername(String username) throws SQLException {
		return (People) this.sqlMapClient.queryForObject("selectPeopleByUsername", username);
	}

	@Override
	public Map<Long, String> getPeopleIdRealNameMapByIds(Set<Long> peopleIds) throws SQLException {
		@SuppressWarnings("unchecked")
		List<People> peoples = (List<People>) this.sqlMapClient.queryForList("selectPeopleIdRealNamesByIds", peopleIds.toArray(new Long[peopleIds.size()]));
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#getPagePeopleRelation(q.dao.page.PeopleRelationPage)
	 */
	@Override
	public List<PeopleRelation> getPagePeopleRelationWithFromRealName(PeopleRelationPage page) throws SQLException {
		List<PeopleRelation> relations = getPagePeopleRelation(page);
		if (CollectionKit.isNotEmpty(relations)) {
			HashSet<Long> peopleIds = new HashSet<Long>(relations.size());
			for (PeopleRelation relation : relations) {
				peopleIds.add(relation.getFromPeopleId());
			}
			Map<Long, String> map = getPeopleIdRealNameMapByIds(peopleIds);
			for (PeopleRelation relation : relations) {
				relation.setFromPeopleRealName(map.get(relation.getFromPeopleId()));
			}
		}
		return relations;
	}
	
	@Override
	public List<PeopleRelation> getPagePeopleRelationWithToRealName(PeopleRelationPage page) throws SQLException {
		List<PeopleRelation> relations = getPagePeopleRelation(page);
		if (CollectionKit.isNotEmpty(relations)) {
			HashSet<Long> peopleIds = new HashSet<Long>(relations.size());
			for (PeopleRelation relation : relations) {
				peopleIds.add(relation.getToPeopleId());
			}
			Map<Long, String> map = getPeopleIdRealNameMapByIds(peopleIds);
			for (PeopleRelation relation : relations) {
				relation.setToPeopleRealName(map.get(relation.getToPeopleId()));
			}
		}
		return relations;
	}

	@Override
	public List<PeopleRelation> getPagePeopleRelation(PeopleRelationPage page) throws SQLException {
		@SuppressWarnings("unchecked")
		List<PeopleRelation> relations = (List<PeopleRelation>) this.sqlMapClient.queryForList("selectPagePeopleRelation", page);
		return relations;
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
	public void updatePeopleRelationStatusById(PeopleRelationStatus relationStatus, long relationId) throws SQLException {
		PeopleRelation relation = new PeopleRelation();
		relation.setId(relationId);
		relation.setStatus(relationStatus);
		this.sqlMapClient.update("updatePeopleRelationStatusById", relation);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.PeopleDao#updatePeopleRelationStatusByFromIdAndToId(q.domain.PeopleRelationStatus, long, long)
	 */
	@Override
	public void updatePeopleRelationStatusByFromIdAndToId(PeopleRelationStatus status, long fromPeopleId, long toPeopleId) throws SQLException {
		PeopleRelation relation = new PeopleRelation();
		relation.setFromPeopleId(fromPeopleId);
		relation.setToPeopleId(toPeopleId);
		relation.setStatus(status);
		this.sqlMapClient.update("updatePeopleRelationStatusByFromIdToId", relation);
	}

	@Override
	public int getPeopleFollowingNumById(long peopleId) throws SQLException {
		return (Integer) this.sqlMapClient.queryForObject("getPeopleFollowingNumById", peopleId);
	}

	@Override
	public int getPeopleFollowerNumById(long peopleId) throws SQLException {
		return (Integer) this.sqlMapClient.queryForObject("getPeopleFollowerNumById", peopleId);
	}

}
