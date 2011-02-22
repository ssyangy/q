/**
 * 
 */
package q.web.event;

import q.dao.EventDao;
import q.dao.PeopleDao;
import q.domain.Event;
import q.domain.Group;
import q.domain.People;
import q.domain.PeopleJoinEvent;
import q.domain.PeopleJoinGroup;
import q.domain.Status;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 * 
 */
public class GetEvent extends Resource {
	private EventDao eventDao;
	
	private PeopleDao peopleDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long eventId = context.getResourceIdLong();
		Event event = this.eventDao.getEventById(eventId);
		People sendperson = this.peopleDao.getPeopleById(event.getCreatorId());
		context.setModel("senderperson", sendperson);
		context.setModel("event", event);
		long peopleId = context.getLoginPeopleId();
		PeopleJoinEvent join = eventDao.getPeopleJoinEvent(peopleId, eventId);
		if (join != null && join.getStatus() == Status.COMMON.getValue()) {
			context.setModel("join", join);
		}
		log.debug("event:%s, join:%s", event, join);
	}

}
