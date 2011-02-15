package q.biz.peoples;
import java.io.*;
import java.sql.SQLException;
import java.util.Date;


import org.apache.commons.validator.GenericValidator;

import q.dao.PeopleDao;
import q.domain.Gender;
import q.domain.People;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;
public class AddRegister extends Resource{
private final static Logger log = Logger.getLogger();
	
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
@Override
public void execute(ResourceContext context) throws SQLException{
  String uid = context.getResourceLastId();
  People people=new People();
  people.setId(System.currentTimeMillis());
  people.setEmail(context.getString("email"));
  people.setPassword(context.getString("password"));
  people.setUsername(context.getString("username"));
  people.setRealName(context.getString("real_name"));
  people.setLoginToken("xxxx");
  people.setCreated(new Date());
  log.debug("get person's email:%s", people.getEmail());
  log.debug("get person's age:%s", people.getYear());
	peopleDao.addPeople(people);
}
}