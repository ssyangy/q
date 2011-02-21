/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.Date;

import q.dao.EventDao;
import q.domain.Event;
import q.domain.PeopleJoinEvent;
import q.domain.Status;
import q.util.IdCreator;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 * 
 */
public class EventDaoImpl extends AbstractDaoImpl implements EventDao {

	@Override
	public void addEvent(Event event) throws SQLException {
		event.setId(IdCreator.getLongId());
		event.setCreated(new Date());
		event.setStatus(Status.COMMON.getValue());
		this.sqlMapClient.insert("insertEvent", event);
	}

	@Override
	public Event getEventById(long eid) throws SQLException {
		return (Event) this.sqlMapClient.queryForObject("selectEventById", eid);
	}

	@Override
	public void addPeopleJoinEvent(PeopleJoinEvent e) throws SQLException {
	    this.sqlMapClient.insert("insertPeopleJoinEvent", e);
		
	}

	

}
