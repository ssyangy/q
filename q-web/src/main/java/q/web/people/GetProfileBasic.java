package q.web.people;

import q.dao.PeopleDao;
import q.domain.Area;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

public class GetProfileBasic extends Resource{
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		context.setModel("rootArea", Area.getRootArea());
	//	long loginPeopleId = context.getResourceIdLong();
	//	People people = peopleDao.getPeopleById(loginPeopleId);
		long temp=1300368092229L;
		People people = peopleDao.getPeopleById(temp);
		context.setModel("people", people);
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
