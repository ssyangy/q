package q.web.people;

import java.sql.SQLException;
import java.util.List;

import q.dao.DaoHelper;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.WeiboPage;
import q.domain.People;
import q.domain.Weibo;
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

	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		Long peopleId = context.getResourceIdLong();

		People people = peopleDao.getPeopleById(peopleId);
		people.setFollowingNum(999999999);
		people.setFollowNum(99);
		people.setFriendNum(9);
		context.setModel("people", people);

		WeiboPage page = new WeiboPage();
		page.setSenderId(peopleId);
		page.setStartId(0);
		page.setSize(20);
		page.setStartIndex(0);
		List<Weibo> weibos = DaoHelper.getPageWeibo(peopleDao, weiboDao, page);
		context.setModel("weibos", weibos);
		log.debug("people:%s, weibos:%s", people, weibos);
	}

}
