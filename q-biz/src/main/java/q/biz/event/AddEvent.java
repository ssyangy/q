package q.biz.event;

import q.biz.IdCreator;
import q.dao.EventDao;
import q.domain.Event;
import q.web.Resource;
import q.web.ResourceContext;

public class AddEvent extends Resource {
	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		Event event = new Event();
		event.setId(IdCreator.getLongId());

	}

}
