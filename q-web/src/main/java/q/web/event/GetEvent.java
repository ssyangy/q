/**
 * 
 */
package q.web.event;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Event;
import q.domain.PeopleJoinEvent;
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
	
	private GroupDao groupDao;
	
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
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
		context.setModel("event", event);
		DaoHelper.injectEventWithRealName(peopleDao, event);
		DaoHelper.injectEventWithGroupName(groupDao, event);

		long peopleId = context.getCookiePeopleId();
		if (peopleId > 0) {
			PeopleJoinEvent join = eventDao.getPeopleJoinEvent(peopleId, eventId);
			if (join != null && join.getStatus() == Status.COMMON.getValue()) {
				context.setModel("join", join);
			}
		}
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
