package q.web.event;

import java.util.List;

import q.dao.EventDao;
import q.domain.Event;
import q.web.Resource;
import q.web.ResourceContext;



public class GetEventIndex extends Resource{
	private EventDao eventDao;
	
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		List<Event> groups = eventDao.getNewEvents(10);
		context.setModel("newEvents", groups);
	}

}