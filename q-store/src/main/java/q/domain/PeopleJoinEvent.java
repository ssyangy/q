package q.domain;

import java.io.Serializable;
import java.util.Date;

public class PeopleJoinEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6382113643857231843L;

	private long id;

	private long peopleId;

	private String peopleRealName;

	private long eventId;

	private long groupId;

	private int status;

	private Date created;

	private Date modified;

	public PeopleJoinEvent() {
		super();
	}

	/**
	 * @param peopleId2
	 * @param eventId2
	 */
	public PeopleJoinEvent(long peopleId, long eventId) {
		this.peopleId = peopleId;
		this.eventId = eventId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(long peopleId) {
		this.peopleId = peopleId;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "EventJoin [id=" + id + ", peopleId=" + peopleId + ", eventId=" + eventId + ", status=" + status + ", created=" + created + ", modified=" + modified + "]";
	}

	/**
	 * @return
	 */
	public boolean isUnjoinStatus() {
		return this.getStatus() == Status.DELETE.getValue();
	}

	public boolean isJoinStatus() {
		return this.getStatus() == Status.COMMON.getValue();
	}

	public String getPeopleRealName() {
		return peopleRealName;
	}

	public void setPeopleRealName(String peopleRealName) {
		this.peopleRealName = peopleRealName;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

}
