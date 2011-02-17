/**
 * 
 */
package q.dao;

import java.sql.SQLException;

import q.domain.Event;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 *
 */
public interface EventDao {

	void addEvent(Event e) throws SQLException;
	
	Event getEventById(long eventId) throws SQLException;
}
