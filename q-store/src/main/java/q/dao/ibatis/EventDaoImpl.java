/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;

import q.dao.EventDao;
import q.domain.Event;
import q.domain.PeopleEventJoin;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 * 
 */
public class EventDaoImpl extends AbstractDaoImpl implements EventDao {

	@Override
	public void addEvent(Event e) throws SQLException {
		this.sqlMapClient.insert("insertEvent", e);
	}

	@Override
	public Event getEventById(long eid) throws SQLException {
		return (Event) this.sqlMapClient.queryForObject("selectEventById", eid);
	}

	@Override
	public void addPeopleJoinEvent(PeopleEventJoin e) throws SQLException {
	    this.sqlMapClient.insert("insertPeopleJoinEvent", e);
		
	}

	

}
