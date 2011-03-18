/**
 * 
 */
package q.web.login;

import q.dao.PeopleDao;
import q.domain.People;
import q.web.DefaultResourceContext;
import q.web.LoginCookie;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleLoginPasswordException;
import q.web.exception.PeopleNotExistException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 20, 2011
 * 
 */
public class AddLogin extends Resource {
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
		String username = context.getString("username");
		String password = context.getString("password");
//		String fromPath = context.getString("from");

		People people = this.peopleDao.getPeopleByUsername(username);
		if (null == people) {
			throw new PeopleNotExistException("username:用户不存在");
		}
		if (!people.getPassword().equals(password)) { // FIXME incorrect password, wanglin
			throw new PeopleLoginPasswordException("password:密码错误");
		}
		context.setModel("people", people);
		((DefaultResourceContext) context).addLoginCookie(new LoginCookie(people.getId(), people.getRealName(), people.getUsername())); // set login cookie
//		if (fromPath != null) {
//			context.redirectContextPath(fromPath);
//		} else {
//			context.redirectServletPath("/group/feed");
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
