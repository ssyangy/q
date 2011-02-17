/**
 * 
 */
package q.dao;

import java.sql.SQLException;

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
}
