package q.web.event;

import q.dao.EventDao;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotPermitException;

public class DeleteEventJoin extends Resource{
  
	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getCookiePeopleId();
		long eventId = context.getResourceIdLong();
		eventDao.unjoinPeopleJoinEvent(peopleId, eventId);
		//context.redirectReffer();
	}
	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		if (context.getCookiePeopleId() <= 0) {
			throw new PeopleNotPermitException("login:无操作权限");
		}
	}

}
