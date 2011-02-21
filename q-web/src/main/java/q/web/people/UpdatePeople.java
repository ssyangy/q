/**
 * 
 */
package q.web.people;

import q.dao.PeopleDao;
import q.domain.Gender;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author Administrator
 * 
 */
public class UpdatePeople extends Resource {

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getLoginPeopleId();
		People newpeople = new People();
		newpeople.setId(peopleId);
		newpeople.setYear(Integer.parseInt(context.getString("year")));
		newpeople.setMonth(Integer.parseInt(context.getString("month")));
		newpeople.setDay(Integer.parseInt(context.getString("day")));
		newpeople.setGender(Gender.convertValue(context.getInt("gender")));
		peopleDao.updatePeople(newpeople);
		context.redirectPath("/people/" + newpeople.getId());
	}

}
