package q.web.people;

import java.sql.SQLException;

import q.biz.SearchService;
import q.dao.AuthcodeDao;
import q.dao.PeopleDao;
import q.domain.Gender;
import q.domain.People;
import q.util.StringKit;
import q.web.DefaultResourceContext;
import q.web.LoginCookie;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleAlreadyExistException;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author Zhehao
 * @author alin
 * @date Feb 14, 2011
 *
 */

public class AddPeople extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private AuthcodeDao authcodeDao;

	public void setAuthcodeDao(AuthcodeDao authcodeDao) {
		this.authcodeDao = authcodeDao;
	}

	private SearchService searchService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	@Override
	public void execute(ResourceContext context) throws Exception {
		People people = new People();
		people.setEmail(context.getString("email"));
		people.setPassword(context.getString("password"));
		people.setUsername(context.getString("username").toLowerCase());
		people.setRealName(context.getString("realName"));
		people.setGender(Gender.convertValue(context.getInt("gender", 0)));
		people.setLoginToken("xxxx");// FIXME wanglin
		peopleDao.addPeople(people);
		context.setModel("people", people);
		((DefaultResourceContext) context).addLoginCookie(new LoginCookie(people.getId(), people.getRealName(), people.getUsername())); // set login cookie
		searchService.updatePeople(people);
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		PeopleValidator.validateUsername(context.getString("username"));
		PeopleValidator.validateRealName(context.getString("realName"));
		PeopleValidator.validatePassword(context.getString("password"));
		if (!context.getString("confirmPassword").equals(context.getString("password"))) {
			throw new RequestParameterInvalidException("confirmPassword:两次输入的密码不同。");
		}
		String email = context.getString("email");
		if (!PeopleValidator.validateEmail(email)) {
			throw new RequestParameterInvalidException("email:请输入正确的邮箱地址。");
		}
		People result = this.peopleDao.getPeopleByEmail(email);
		if (result != null) {
			throw new PeopleAlreadyExistException("email:该邮箱地址已经被使用。");
		}
		String username=context.getString("username");
		People result2 = this.peopleDao.getPeopleByUsername(username);
		if (result2 != null) {
			throw new PeopleAlreadyExistException("username:该用户名已经被使用。");
		}
		long authcodeId = context.getIdLong("authcodeId");
		String authcodeValue = authcodeDao.getValueById(authcodeId);
		if (StringKit.isEmpty(authcodeValue) || !authcodeValue.equals(context.getString("authcode"))) {
			throw new RequestParameterInvalidException("authcode:验证码不对,请重新输入。");
		}
		authcodeDao.updateValueById(authcodeId);
	}

}
