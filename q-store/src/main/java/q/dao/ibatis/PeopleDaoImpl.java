/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import q.dao.PeopleDao;
import q.domain.People;

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
	public Map<Long, String> getPeopleRealNamesByIds(Set<Long> peopleIds) throws SQLException {
		Long[] ids = peopleIds.toArray(new Long[peopleIds.size()]);
		Object[] names = this.sqlMapClient.queryForList("selectPeopleRealNamesByIds", ids).toArray();
		Map<Long, String> map = new HashMap<Long, String>(ids.length);
		for(int i = 0; i < ids.length; i++) {
			map.put(ids[i], (String)names[i]);
		}
		return map;
	}

}
