/**
 * 
 */
package q.biz.event;

import q.dao.EventDao;
import q.domain.Event;
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

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
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
		context.setModel("event", event);
	}

}