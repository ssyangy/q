/**
 * 
 */
package q.dao.page;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 25, 2011
 * 
 */
public class PeopleJoinEventPage extends Page {

	private static final long serialVersionUID = -8251317312599519537L;

	private Long eventId;

	private Long peopleId;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}

}
