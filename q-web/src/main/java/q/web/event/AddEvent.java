package q.web.event;

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
		event.setIntro(context.getString("intro"));
		event.setName(context.getString("name"));
		event.setGroupId(context.getLong("groupId"));
		event.setCreatorId(context.getLoginPeopleId());
		this.eventDao.addEvent(event);
		context.redirectPath("/event/" + event.getId());
	}

}
