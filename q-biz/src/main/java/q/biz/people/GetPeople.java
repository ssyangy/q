package q.biz.people;

import java.sql.SQLException;

import q.dao.PeopleDao;
import q.domain.People;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
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
		long uid = Long.valueOf(resourceId);

		People people = peopleDao.getPeopleById(uid);
		people.setFollowingNum(999999999);
		people.setFollowNum(99);
		people.setFriendNum(9);

		log.debug("get people:%s", people);
		context.setModel("people", people);
	}

}
