package q.web.event;

import q.dao.EventDao;
import q.web.Resource;
import q.web.ResourceContext;

public class DeleteEventJoin extends Resource{
  
	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getLoginPeopleId();
		long eventId = context.getResourceIdLong();
		eventDao.unjoinPeopleJoinEvent(peopleId, eventId);
		context.redirectReffer();
	}

}
