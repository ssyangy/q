/**
 * 
 */
package q.dao;

import java.sql.SQLException;

import q.domain.Event;
import q.domain.PeopleJoinEvent;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 *
 */
public interface EventDao {

	void addEvent(Event event) throws SQLException;
	
	Event getEventById(long eventId) throws SQLException;
	
	void addPeopleJoinEvent(PeopleJoinEvent join)throws SQLException;
}
