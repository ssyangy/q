package q.web.people;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Group;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotExistException;

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

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		long peopleId = context.getResourceIdLong();
		People people;
		if(peopleId == 0) {
			String username = context.getResourceId();
			people = peopleDao.getPeopleByUsername(username);
		} else {
			people = peopleDao.getPeopleById(peopleId);
		}
		if(people == null) {
			throw new PeopleNotExistException();
		}
		if (loginPeopleId > 0 && loginPeopleId != people.getId()) {
			DaoHelper.injectPeopleWithVisitorRelation(peopleDao, people, loginPeopleId);
		}
		if (loginPeopleId > 0) {
			DaoHelper.injectPeopleWithSelf(people, loginPeopleId);
		} 
		context.setModel("people", people);
		if (!context.isApiRequest()) {
			List<Group> groups = groupDao.getGroupsByJoinPeopleId(people.getId());
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
//		long peopleId = context.getResourceIdLong();
//		if (IdCreator.isNotValidId(peopleId)) {
//			throw new RequestParameterInvalidException("people:invalid");
//		}

	}
}
