package q.web.people;

import q.dao.AreaDao;
import q.dao.PeopleDao;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

public class GetPeopleFull extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private AreaDao areaDao;

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long id = context.getResourceIdLong();
		context.setModel("rootArea", areaDao.getRootArea());
		People people = peopleDao.getPeopleById(id);
		context.setModel("people", people);
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		// FIXME wanglin

	}

}
