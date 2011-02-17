package q.biz.people;
import java.sql.SQLException;
import java.util.Date;
import q.dao.PeopleDao;
import q.domain.People;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;
/**
 * @author Zhehao
 * @date Feb 14, 2011
 *
 */

public class AddPeople extends Resource{

private final static Logger log = Logger.getLogger();
	
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
@Override
public void execute(ResourceContext context) throws SQLException{
  People people=new People();
  people.setId(System.currentTimeMillis());
  people.setEmail(context.getString("email"));
  people.setPassword(context.getString("password"));
  people.setUsername(context.getString("username"));
  people.setRealName(context.getString("real_name"));
  people.setLoginToken("xxxx");
  people.setCreated(new Date());
  context.setModel("idd", people.getId());
  log.debug("get person's id:%s", people.getId());
  peopleDao.addPeople(people);
}
}