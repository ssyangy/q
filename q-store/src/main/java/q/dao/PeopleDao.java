/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import q.domain.People;

/**
 * @author alin
 * @date Feb 10, 2011
 * 
 */
public interface PeopleDao {

	void addPeople(People p) throws SQLException;

	People getPeopleById(long pid) throws SQLException;
	
	void updatePeople(People p)throws SQLException;

	/**
	 * @param username
	 * @return 
	 * @throws SQLException 
	 */
	People getPeopleByUsername(String username) throws SQLException;

	/**
	 * @param senderIds
	 * @return
	 */
	Map<Long, String> getPeopleRealNamesByIds(Set<Long> senderIds) throws SQLException;
}
