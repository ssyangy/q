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
		People people = this.peopleDao.getPeopleByEmail(username);
		if (null == people) {
			throw new PeopleNotExistException("username:邮箱不存在");
		}
		if (!people.getPassword().equals(password)) {
			throw new PeopleLoginPasswordException("password:密码错误");
		}
		context.setModel("people", people);
		((DefaultResourceContext) context).addLoginCookie(new LoginCookie(people.getId(), people.getRealName(), people.getUsername())); // set login cookie
		String from = context.getString("from"); // redirect to origin refferr
		if (from == null) {
			context.redirectServletPath("");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		
	}

}
