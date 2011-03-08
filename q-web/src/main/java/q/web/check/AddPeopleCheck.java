package q.web.check;

import q.dao.PeopleDao;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

public class AddPeopleCheck extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		String email = context.getString("email");
		People result = this.peopleDao.getPeopleByEmail(email);
		context.setModel("result", result);
	}

}
