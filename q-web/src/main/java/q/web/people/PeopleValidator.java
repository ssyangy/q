package q.web.people;

import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;

import q.util.StringKit;
import q.web.exception.RequestParameterInvalidException;

public class PeopleValidator {
	/**
	 * 验证email
	 */
	public static boolean validateEmail(String email) {
		if (StringKit.isEmpty(email)) {
			return false;
		}
		return GenericValidator.isEmail(email);
	}

	/**
	 * 验证圈子帐号,
	 * @throws RequestParameterInvalidException 
	 */
	public static void validateUsername(String account) throws RequestParameterInvalidException {
		if (StringKit.isEmpty(account)) {
			throw new RequestParameterInvalidException("username:用户名必填");
		}
		Pattern p = Pattern.compile("^[a-zA-Z0-9_/-]{6,16}$");
		if(!p.matcher(account).matches()) {
			throw new RequestParameterInvalidException("username:用户名允许6-16个字符,包括英文字母,数字,下划线,减号");
		}

	}

	/**
	 * 验证密码
	 * @throws RequestParameterInvalidException 
	 */
	public static void validatePassword(String password) throws RequestParameterInvalidException {
		if (StringKit.isEmpty(password)) {
			throw new RequestParameterInvalidException("password:密码不能为空");
		}
		Pattern p = Pattern.compile("^.{6,16}$");
		if (!p.matcher(password).matches()){
			throw new RequestParameterInvalidException("password:密码允许6-16个字符,区分大小写");
		}
	}

	/**
	 * 验证姓名
	 * @throws RequestParameterInvalidException 
	 */
	public static void validateRealName(String realName) throws RequestParameterInvalidException {
		if (StringKit.isEmpty(realName)) {
			throw new RequestParameterInvalidException("realName:昵称不能为空");
		}
		//Pattern p = Pattern.compile("^[a-zA-Z0-9_/-\u4e00-\u9fa5]+{0,11}$");
		Pattern p = Pattern.compile("^.{1,12}$");
		if (!p.matcher(realName).matches()) {
			throw new RequestParameterInvalidException("realName:昵称允许1-12个字符,区分大小写");
		}
	}

}
