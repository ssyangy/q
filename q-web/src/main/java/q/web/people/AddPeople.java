package q.web.people;

import java.sql.SQLException;
import java.util.Random;

import q.dao.AuthcodeDao;
import q.dao.PeopleDao;
import q.domain.Gender;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author Zhehao
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

	@Override
	public void execute(ResourceContext context) throws SQLException {
		People people = new People();
		people.setEmail(context.getString("email"));
		people.setPassword(context.getString("password"));
		people.setUsername(context.getString("username"));
		people.setRealName(context.getString("real_name"));
		people.setGender(Gender.convertValue(context.getInt("gender", 0)));
		people.setLoginToken("xxxx");
		peopleDao.addPeople(people);
		context.setModel("id", people.getId());
		context.setModel("email", people.getEmail());
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		String email = context.getString("email");
		long authcodeId = Long.parseLong(context.getString("authcodeId"));
		try {
			String value = authcodeDao.getValueById(authcodeId);
			if (!PeopleValidator.validateEmail(email)) {
				context.setModel("wrong", "请输入正确的邮箱地址。");
				context.setModel("errorkind", 1);
				throw new InvalidEmailException();
			}
			// TODO check email exists
			if (!PeopleValidator
					.validatePassword(context.getString("password"))) {
				context.setModel("wrong",
						"密码少于6位,或者有包含有数字,字母,下划线以外的字符。");
				context.setModel("errorkind", 2);
				throw new InvalidPasswordException();
			}
			if (!context.getString("confirmPassword").equals(
					context.getString("password"))) {
				context.setModel("wrong", "两次输入的密码不同。");
				context.setModel("errorkind", 3);

				throw new InvalidConfirmPasswordException();
			}
			if (context.getString("username") == "") {
				context.setModel("wrong", "用户名不能为空。");
				context.setModel("errorkind", 4);

				throw new InvalidUsernameException();
			}
			if (context.getString("realName") == "") {
				context.setModel("wrong", "昵称不能为空。");
				context.setModel("errorkind", 5);

				throw new InvalidRealNameException();
			}
			if (!context.getString("authcode").equals(value)) {
				context.setModel("wrong", "验证码不对,重新输入下吧。");
				context.setModel("errorkind", 6);

				throw new InvalidAuthcodeException();
			}
		} catch (Exception e) {
			context.setModel("email", email);
            context.setModel("password", context.getString("password"));
            context.setModel("username", context.getString("username"));
            context.setModel("real_name", context.getString("real_name"));
            context.setModel("confirmPassword", context.getString("confirmPassword"));
            context.setModel("authcode", context.getString("authcode"));
			context.forward("/WEB-INF/jsp/getPeopleNew.jsp");
			throw e;
		}
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(rand.nextInt(10));
		}
		authcodeDao.updateValueById(authcodeId, sb.toString());

	}

}
