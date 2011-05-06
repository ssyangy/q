package q.web.check;

import org.apache.commons.lang.StringUtils;

import q.dao.PeopleDao;
import q.domain.People;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleAlreadyExistException;
import q.web.exception.PeopleNotExistException;
import q.web.exception.RequestParameterInvalidException;

/**
 * Check people exist or not exist, with email or username.
 * 
 * @author seanlinwang at gmail dot com
 * @date May 6, 2011
 * 
 */
public class AddPeopleCheck extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		String email = context.getString("email");
		String exist = StringUtils.trimToNull(context.getString("exist"));

		if (StringKit.isNotEmpty(email)) {
			People result = this.peopleDao.getPeopleByEmail(email);
			if (exist == null) { // except not exists
				if (result != null) {// but exists
					throw new PeopleAlreadyExistException("email:该邮箱地址已经被使用");
				}
			} else { // except exists
				if (result == null) { // but not exists
					throw new PeopleNotExistException("email:该邮箱地址不存在");
				}
			}
		}

		String username = context.getString("username");

		if (StringKit.isNotEmpty(username)) {
			People result = this.peopleDao.getPeopleByUsername(username);
			if (exist == null) {// except not exists
				if (result != null) { // but exists
					throw new PeopleAlreadyExistException("username:该用户名已经被使用");
				}
			} else { // except exists
				if (result == null) { // but not exists
					throw new PeopleNotExistException("username:该用户名不存在");
				}
			}
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		String email = context.getString("email");
		String username = context.getString("username");
		if (StringKit.isEmpty(email) && StringKit.isEmpty(username)) {
			throw new RequestParameterInvalidException("all:参数丢失");
		}
	}
}
