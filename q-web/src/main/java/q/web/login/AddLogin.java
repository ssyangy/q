/**
 * 
 */
package q.web.login;

import q.dao.PeopleDao;
import q.domain.People;
import q.log.Logger;
import q.web.DefaultResourceContext;
import q.web.LoginCookie;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 20, 2011
 * 
 */
public class AddLogin extends Resource {
	private final static Logger log = Logger.getLogger();

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
		String fromPath = context.getString("from");
		log.debug("execute AddLogin.java using username:%s password:%s from:%s", username, password, fromPath);

		People p = this.peopleDao.getPeopleByUsername(username);
		if (!p.getPassword().equals(password)) { // FIXME incorrect password
			throw new IncorrectPasswordLoginException(username);
		}
		((DefaultResourceContext) context).addLoginCookie(new LoginCookie(p.getId(), p.getRealName(), p.getUsername())); // set login cookie
		if (fromPath != null) {
			context.redirectContextPath(fromPath);
		}
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
