package q.web.people;

import java.sql.SQLException;
import java.util.Date;

import q.dao.PeopleDao;
import q.domain.Gender;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author Zhehao
 * @date Feb 14, 2011
 * 
 */

public class AddPeople extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		People people = new People();
		people.setId(System.currentTimeMillis());
		people.setEmail(context.getString("email"));
		people.setPassword(context.getString("password"));
		people.setUsername(context.getString("username"));
		people.setRealName(context.getString("real_name"));
		people.setGender(Gender.convertValue(context.getInt("gender")));
		people.setLoginToken("xxxx");
		people.setCreated(new Date());
		context.setModel("idd", people.getId());
		log.debug("add person %s", people);
		peopleDao.addPeople(people);
	}
}