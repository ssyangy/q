/**
 * 
 */
package q.biz.event;

import q.dao.EventDao;
import q.dao.PeopleDao;
import q.domain.Event;
import q.domain.People;
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
		this.peopleDao=peopleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long eventId = context.getResourceLastIdLong();
		Event event = this.eventDao.getEventById(eventId);
		People sendperson=this.peopleDao.getPeopleById(event.getSenderId());
		context.setModel("senderperson", sendperson);
		context.setModel("event", event);
	}

}
