package q.web.event;

import java.util.Date;

import q.dao.EventDao;
import q.domain.PeopleJoinEvent;
import q.web.Resource;
import q.web.ResourceContext;

public class AddEventJoin extends Resource{
  
	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		PeopleJoinEvent peopleeventjoin=new PeopleJoinEvent();
		peopleeventjoin.setId(System.currentTimeMillis());
		peopleeventjoin.setCreated(new Date());
		peopleeventjoin.setPeopleId(1);//FIXME
		peopleeventjoin.setEventId(Long.parseLong(context.getString("eventId")));
		peopleeventjoin.setStatus(0);
		eventDao.addPeopleJoinEvent(peopleeventjoin);
		
	}

}
