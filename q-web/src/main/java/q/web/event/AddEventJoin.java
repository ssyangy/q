package q.web.event;

import q.dao.EventDao;
import q.domain.PeopleJoinEvent;
import q.web.Resource;
import q.web.ResourceContext;

public class AddEventJoin extends Resource {

	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getCookiePeopleId();
		long eventId = context.getResourceIdLong();
		PeopleJoinEvent join = eventDao.getPeopleJoinEvent(peopleId, eventId);
		if (join == null) {
			eventDao.addPeopleJoinEvent(peopleId, eventId);
		} else if (join.isUnjoinStatus()) {
			eventDao.rejoinPeopleJoinEvent(peopleId, eventId);
		}

		//context.redirectReffer();

	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
