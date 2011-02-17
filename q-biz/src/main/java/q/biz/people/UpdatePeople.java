/**
 * 
 */
package q.biz.people;
import q.dao.PeopleDao;
import q.domain.Gender;
import q.domain.People;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;
/**
 * @author Administrator
 *
 */
public class UpdatePeople extends Resource {

	private PeopleDao peopleDao;
	private final static Logger log = Logger.getLogger();
	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}
	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		 String temp=context.getString("id");
		 temp=temp.substring(0,temp.length()-1);
		 log.debug("get people:%s", temp);
		 long id=Long.parseLong(temp);
	     People newpeople=new People();
	     newpeople.setId(id);
	     newpeople.setYear(Integer.parseInt(context.getString("year")));
	     newpeople.setMonth(Integer.parseInt(context.getString("month")));
	     newpeople.setDay(Integer.parseInt(context.getString("day")));
	     newpeople.setGender(Gender.convertValue(context.getInt("gender")));
	     peopleDao.updatePeople(newpeople);
	}

}
