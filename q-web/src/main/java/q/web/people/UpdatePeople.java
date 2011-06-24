/**
 * 
 */
package q.web.people;

import q.dao.PeopleDao;
import q.domain.People;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;

/**
 * @author seanlinwang
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
		long peopleId = context.getCookiePeopleId();
		People newpeople = new People();
		newpeople.setId(peopleId);
		newpeople.setYear(Integer.parseInt(context.getString("year")));
		newpeople.setMonth(Integer.parseInt(context.getString("month")));
		newpeople.setDay(Integer.parseInt(context.getString("day")));
		peopleDao.updatePeopleById(newpeople);
		context.redirectServletPath("/people/" + newpeople.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
	}

}
