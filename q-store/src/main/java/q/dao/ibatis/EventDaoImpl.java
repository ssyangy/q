/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.EventDao#getNewEvents(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getNewEvents(int limit) throws SQLException {
		return (List<Event>) this.sqlMapClient.queryForList("selectNewEvents", limit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.EventDao#getPeopleJoinEvent(long, long)
	 */
	@Override
	public PeopleJoinEvent getPeopleJoinEvent(long peopleId, long eventId) throws SQLException {
		return (PeopleJoinEvent) this.sqlMapClient.queryForObject("selectPeopleJoinEvetByPeopleIdAndEventId", new PeopleJoinEvent(peopleId, eventId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.EventDao#addPeopleJoinEvent(long, long)
	 */
	@Override
	public void addPeopleJoinEvent(long peopleId, long eventId) throws SQLException {
		PeopleJoinEvent join = new PeopleJoinEvent();
		join.setPeopleId(peopleId);
		join.setEventId(eventId);

		join.setId(IdCreator.getLongId());
		this.sqlMapClient.insert("insertPeopleJoinEvent", join);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.EventDao#rejoinPeopleJoinGroup(long, long)
	 */
	@Override
	public void rejoinPeopleJoinEvent(long peopleId, long eventId) throws SQLException {
		PeopleJoinEvent join = new PeopleJoinEvent();
		join.setPeopleId(peopleId);
		join.setEventId(eventId);
		join.setStatus(Status.COMMON.getValue());
		this.sqlMapClient.update("updatePeopleJoinEventStatusByPeopleIdAndEventId", join);
	}

	/* (non-Javadoc)
	 * @see q.dao.EventDao#unjoinPeopleJoinEvent(long, long)
	 */
	@Override
	public void unjoinPeopleJoinEvent(long peopleId, long eventId) throws SQLException {
		PeopleJoinEvent join = new PeopleJoinEvent();
		join.setPeopleId(peopleId);
		join.setEventId(eventId);
		join.setStatus(Status.DELETE.getValue());
		this.sqlMapClient.update("updatePeopleJoinEventStatusByPeopleIdAndEventId", join);
	}

}
