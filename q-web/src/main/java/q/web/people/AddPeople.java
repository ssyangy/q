package q.web.people;

import java.sql.SQLException;

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
		context.setModel("idd", people.getId());
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
        String email=context.getString("email");
        if(!PeopleValidator.validateEmail(email)){
          context.setModel("emailWrong", "请输入正确的邮箱地址。");
          throw new InvalidEmailException();
        }
        //TODO check email exists
        if(!PeopleValidator.validatePassword(context.getString("password"))){
          context.setModel("passwordWrong", "密码少于6位,或者有包含有数字,字母,下划线以外的字符。");
          throw new InvalidPasswordException();
        }
        if(context.getString("confirmPassword")!=context.getString("password")){
          context.setModel("confirmPasswordWrong", "两次输入的密码不同。");
          throw new InvalidConfirmPasswordException();
        }
        if(context.getString("username")!=""){
            context.setModel("usernameWrong", "用户名不能为空。");
            throw new InvalidUsernameException();
          }
        if(context.getString("realName")!=""){
            context.setModel("realNameWrong", "昵称不能为空。");
            throw new InvalidRealNameException();
          }
        if(context.getString("authcode")!="8888"){
        	context.setModel("authcodeWrong", "验证码不对,重新输入下吧。");
            throw new InvalidAuthcodeException();
        }
		super.validate(context);
	}


}
