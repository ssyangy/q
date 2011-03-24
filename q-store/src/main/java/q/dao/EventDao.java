/**
 * 
 */
package q.dao;

import java.sql.SQLException;
import java.util.List;

import q.dao.page.EventPage;
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

	/**
	 * @param
	 * @return
	 */
	List<Event> getPageEvents(EventPage page) throws SQLException;

	/**
	 * @param peopleId
	 * @param eventId
	 * @return
	 */
	PeopleJoinEvent getPeopleJoinEvent(long peopleId, long eventId) throws SQLException;

	/**
	 * @param peopleId
	 * @return
	 * @throws SQLException
	 */
	List<Event> getEventsByParticipantId(long peopleId) throws SQLException;

	/**
	 * @param eventIds
	 * @return
	 * @throws SQLException
	 */
	List<Event> getEventsByIds(List<Long> eventIds) throws SQLException;

	/**
	 * @param peopleId
	 * @param eventId
	 */
	void addPeopleJoinEvent(long peopleId, long eventId) throws SQLException;

	/**
	 * @param peopleId
	 * @param eventId
	 */
	void rejoinPeopleJoinEvent(long peopleId, long eventId) throws SQLException;

	/**
	 * @param loginPeopleId
	 * @param resourceIdLong
	 */
	void unjoinPeopleJoinEvent(long peopleId, long eventId) throws SQLException;

	/**
	 * @param i
	 * @return
	 */
	List<Event> getHotEvents(int limit) throws SQLException;

	/**
	 * @param groupIds
	 * @param limit
	 * @param start
	 * @return
	 */
	List<Event> getEventsByGroupIds(List<Long> groupIds, int limit, int start) throws SQLException;

	/**
	 * @param groupId
	 * @param limit
	 * @param start
	 * @return
	 */
	List<Event> getEventsByGroupId(long groupId, int limit, int start) throws SQLException;
}
