package q.web.event;

import java.util.List;

import q.dao.EventDao;
import q.dao.page.EventPage;
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
		EventPage page = new EventPage();
		page.setSize(10);
		List<Event> groups = eventDao.getPageEvents(page);
		context.setModel("newEvents", groups);
	}

}
