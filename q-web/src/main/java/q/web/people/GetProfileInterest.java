package q.web.people;

import q.dao.PeopleDao;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

public class GetProfileInterest extends Resource{
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;

	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getCookiePeopleId();
		//long peopleId=1300368092229l;
		People people = peopleDao.getInterestById(peopleId);
		context.setModel("people", people);

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
