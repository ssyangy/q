package q.web.event;

import java.text.SimpleDateFormat;

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
		event.setGroupId(context.getIdLong("groupId"));
		event.setCreatorId(context.getLoginPeopleId());
		event.setAddress(context.getString("address"));
		event.setCost(context.getString("cost"));
		// event.setDistrictId(1); FIXME
		event.setNumber(context.getInt("number", 0));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		event.setStarted(df.parse(context.getString("startDate") + context.getString("endTime")));
		event.setEnded(df.parse(context.getString("endDate") + context.getString("endTime")));
		this.eventDao.addEvent(event);
		context.redirectServletPath("/event/" + event.getId());
	}

}
