package q.web.people;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Jan 18, 2011
 * 
 */
public class GetPeople extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		if (!context.isApiRequest()) {
			GetPeopleFrame frame = new GetPeopleFrame();
			frame.setEventDao(eventDao);
			frame.setGroupDao(groupDao);
			frame.setPeopleDao(peopleDao);
			frame.validate(context);
			frame.execute(context);
		} else {
			long loginPeopleId = context.getResourceIdLong();
			People people = peopleDao.getPeopleById(loginPeopleId);
			if (loginPeopleId > 0) {
				DaoHelper.injectPeopleWithVisitorRelation(peopleDao, people, loginPeopleId);
			}
			context.setModel("people", people);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {

	}
}
