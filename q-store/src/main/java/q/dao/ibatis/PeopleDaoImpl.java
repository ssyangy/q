/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;

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
		this.sqlMapClient.update("updatePeopleById",p);
		
	}

}
