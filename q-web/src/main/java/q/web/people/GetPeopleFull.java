package q.web.people;

import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Area;
import q.domain.Degree;
import q.domain.People;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.PeopleNotPermitException;

public class GetPeopleFull extends Resource {
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
		if (!context.isApiRequest()) {
			context.setModel("rootArea", Area.getRootArea());
			context.setModel("degrees", Degree.getAll());
			context.setModel("groups", groupDao.getHotGroups(10));
			long loginPeopleId = context.getResourceIdLong();
			People people = peopleDao.getPeopleById(loginPeopleId);
			context.setModel("people", people);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
		long peopleId = context.getResourceIdLong();
		if (peopleId != loginPeopleId) {
			throw new PeopleNotPermitException();
		}
	}

}
