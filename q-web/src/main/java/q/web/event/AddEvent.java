package q.web.event;

import java.text.SimpleDateFormat;

import q.dao.EventDao;
import q.domain.Area;
import q.domain.Event;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.area.AreaValidator;

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
		event.setCreatorId(context.getCookiePeopleId());
		event.setAddress(context.getString("address"));
		event.setCost(context.getString("cost"));
		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		int areaId = AreaValidator.getAreaId(provinceId, cityId, countyId);
		event.setArea(Area.getAreaById(areaId)); 
		event.setNumber(context.getInt("number", 0));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		event.setStarted(df.parse(context.getString("startDate") + context.getString("endTime")));
		event.setEnded(df.parse(context.getString("endDate") + context.getString("endTime")));
		this.eventDao.addEvent(event);
		context.redirectServletPath("/event/" + event.getId());
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		int provinceId = context.getInt("province", -1);
		int cityId = context.getInt("city", -1);
		int countyId = context.getInt("county", -1);
		AreaValidator.check(provinceId, cityId, countyId);
	}

}
