package q.web.people;

import q.dao.PeopleDao;
import q.domain.Area;
import q.domain.People;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;

public class GetProfileBasic extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		context.setModel("rootArea", Area.getRootArea());
		long peopleId = context.getCookiePeopleId();
		People people = peopleDao.getPeopleById(peopleId);
		context.setModel("people", people);
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
	}

}
