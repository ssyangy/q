package q.web.event;

import q.dao.EventDao;
import q.domain.Event;
import q.domain.PeopleJoinEvent;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.RequestParameterInvalidException;

public class AddEventJoin extends Resource {

	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getCookiePeopleId();
		long eventId = context.getResourceIdLong();
		Event event = this.eventDao.getEventById(eventId);
		if (event == null) {
			throw new RequestParameterInvalidException("eventId:" + eventId);
		}
		PeopleJoinEvent join = eventDao.getPeopleJoinEvent(peopleId, eventId);
		if (join == null) {
			eventDao.addPeopleJoinEvent(peopleId, eventId, event.getGroupId());
		} else if (join.isUnjoinStatus()) {
			eventDao.rejoinPeopleJoinEvent(peopleId, eventId);
		}

		// context.redirectReffer();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
	}

}
