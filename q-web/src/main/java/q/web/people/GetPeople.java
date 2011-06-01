package q.web.people;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Group;
import q.domain.People;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

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
		long loginPeopleId = context.getCookiePeopleId();
		long peopleId = context.getResourceIdLong();
		People people = peopleDao.getPeopleById(peopleId);
		if (IdCreator.isValidIds(loginPeopleId) && loginPeopleId != peopleId) {
			DaoHelper.injectPeopleWithVisitorRelation(peopleDao, people, loginPeopleId);
		}
		context.setModel("people", people);
		if (!context.isApiRequest()) {
			List<Group> groups = groupDao.getGroupsByJoinPeopleId(peopleId);
			context.setModel("groups", groups);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long peopleId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(peopleId)) {
			throw new RequestParameterInvalidException("people:invalid");
		}

	}
}
