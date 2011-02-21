package q.web.event;

import java.util.Date;

import q.dao.EventDao;
import q.domain.PeopleJoinEvent;
import q.web.Resource;
import q.web.ResourceContext;

public class DeleteEventJoin extends Resource{
  
	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		
	}

}
