/**
 * 
 */
package q.dao.page;

import java.util.List;

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
	
	private Long groupId;
	
	private List<Long> groupIds;

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

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public List<Long> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<Long> groupIds) {
		this.groupIds = groupIds;
	}

}
