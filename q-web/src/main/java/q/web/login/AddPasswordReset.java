/**
 *
 */
package q.web.login;

import q.biz.CacheService;
import q.biz.PasswordResetToken;
import q.dao.PeopleDao;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author seanlinwang at gmail dot com
 * @date May 6, 2011
 *
 */
public class AddPasswordReset extends Resource {
	private CacheService cacheService;

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

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
		String key = context.getString("key");
		PasswordResetToken token = this.cacheService.getPasswordResetToken(key);
		if (token == null) {
			throw new RequestParameterInvalidException("key:invalid");
		}
		String newPassword = context.getString("newPassword");
		People people = this.peopleDao.getPeopleById(token.getPeopleId());
		this.peopleDao.updatePasswordByPeopleId(token.getPeopleId(), newPassword);
		this.cacheService.removePasswordResetToken(key);// remove token after password reset
		//((DefaultResourceContext) context).addLoginCookie(new LoginCookie(people.getId(), people.getRealName(), people.getUsername())); // set login cookie

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
//		String oldPassword = context.getString("oldPassword");
//		if (oldPassword == null) {
//			throw new RequestParameterInvalidException("oldPassword:invalid");
//		}
//		String newPassword = context.getString("newPassword");
//		if (newPassword == null) {
//			throw new RequestParameterInvalidException("oldPassword:invalid");
//		}
		String key = context.getString("key");
		if (key == null) {
			throw new RequestParameterInvalidException("key:invalid");
		}
	}

}
