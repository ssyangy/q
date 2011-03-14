package q.web.people;

import q.dao.PeopleDao;
import q.web.Resource;
import q.web.ResourceContext;

public class GetPeopleFull extends Resource{
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
      String id=context.getResourceId();

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}


}
