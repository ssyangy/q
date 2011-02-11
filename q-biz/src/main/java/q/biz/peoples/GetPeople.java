package q.biz.peoples;

import java.sql.SQLException;

import q.dao.PeopleDao;
import q.domain.People;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @date Jan 18, 2011
 * 
 */
public class GetPeople extends Resource {
	private final static Logger log = Logger.getLogger();
	
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		String resourceId = context.getResourceLastId();
		log.debug("resource id:%s", resourceId);
		int uid = Integer.valueOf(resourceId);
		People people = peopleDao.getPeopleById(uid);
		log.debug("getPeople:%s", people.getRealName());
		long fansNum = 100;
		long friendsNum = 20;
		context.setModel("people", people);
		context.setModel("fansNum", fansNum);
		context.setModel("friendsNum", friendsNum);
	}

}
