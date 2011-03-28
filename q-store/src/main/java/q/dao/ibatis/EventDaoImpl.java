/**
 * 
 */
package q.dao.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import q.dao.EventDao;
import q.dao.page.EventPage;
import q.dao.page.PeopleJoinEventPage;
import q.domain.Event;
import q.domain.PeopleJoinEvent;
import q.domain.Status;
import q.util.CollectionKit;
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
	public List<Event> getPageEvents(EventPage page) throws SQLException {
		return (List<Event>) this.sqlMapClient.queryForList("selectPageEvents", page);
	}

	@Override
	public List<Event> getHotEvents(int limit) throws SQLException {
		EventPage page = new EventPage();
		page.setSize(limit);
		page.setStartIndex(0);
		return this.getPageEvents(page);
	}

	@Override
	public List<Event> getEventsByGroupIds(List<Long> groupIds, int limit, int start) throws SQLException {
		EventPage page = new EventPage();
		page.setSize(limit);
		page.setStartIndex(start);
		page.setGroupIds(groupIds);
		return this.getPageEvents(page);
	}

	@Override
	public List<Event> getEventsByGroupId(long groupId, int limit, int start) throws SQLException {
		EventPage page = new EventPage();
		page.setSize(limit);
		page.setStartIndex(start);
		page.setGroupId(groupId);
		return this.getPageEvents(page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.EventDao#getPeopleJoinEvent(long, long)
	 */
	@Override
	public PeopleJoinEvent getPeopleJoinEvent(long peopleId, long eventId) throws SQLException {
		return (PeopleJoinEvent) this.sqlMapClient.queryForObject("selectPeopleJoinEventByPeopleIdAndEventId", new PeopleJoinEvent(peopleId, eventId));
	}

	@Override
	public List<PeopleJoinEvent> getPeopleJoinEventsById(long eventId, int limit, int start) throws SQLException {
		PeopleJoinEventPage page = new PeopleJoinEventPage();
		page.setEventId(eventId);
		page.setSize(limit);
		page.setStartIndex(start);
		List<PeopleJoinEvent> joins = getPeopleJoinEventsByPage(page);
		return joins;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PeopleJoinEvent> getPeopleJoinEventsByPage(PeopleJoinEventPage page) throws SQLException {
		return this.sqlMapClient.queryForList("selectPeopleJoinEventsByPage", page);
	}

	@Override
	public List<Event> getAllEventsByPeopleId(long peopleId) throws SQLException {
		PeopleJoinEventPage page = new PeopleJoinEventPage();
		page.setPeopleId(peopleId);
		List<PeopleJoinEvent> joins = getPeopleJoinEventsByPage(page);

		if (CollectionKit.isEmpty(joins)) {
			return null;
		}
		List<Long> eventIds = new ArrayList<Long>(joins.size());
		for (PeopleJoinEvent join : joins) {
			eventIds.add(join.getEventId());
		}
		return getEventsByIds(eventIds);
	}

	/**
	 * @param eventIds
	 * @return
	 * @throws SQLException
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Event> getEventsByIds(List<Long> eventIds) throws SQLException {
		return (List<Event>) this.sqlMapClient.queryForList("selectEventsByIds", eventIds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.EventDao#addPeopleJoinEvent(long, long)
	 */
	@Override
	public void addPeopleJoinEvent(long peopleId, long eventId, long groupId) throws SQLException {
		PeopleJoinEvent join = new PeopleJoinEvent();
		join.setPeopleId(peopleId);
		join.setEventId(eventId);
		join.setGroupId(groupId);
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

	/*
	 * (non-Javadoc)
	 * 
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.dao.EventDao#getJoinNumberByEventId(long)
	 */
	@Override
	public int getJoinNumberById(long eventId) throws SQLException {
		return (Integer) this.sqlMapClient.queryForObject("selectJoinNumberById", eventId);
	}

}
