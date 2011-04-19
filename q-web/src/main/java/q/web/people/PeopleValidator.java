package q.web.people;

import java.util.regex.Pattern;

import org.apache.commons.validator.GenericValidator;

import q.domain.Gender;
import q.util.IdCreator;
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
	 * 
	 * @throws RequestParameterInvalidException
	 */
	public static void validateUsername(String account) throws RequestParameterInvalidException {
		if (StringKit.isEmpty(account)) {
			throw new RequestParameterInvalidException("username:用户名必填");
		}
		Pattern p = Pattern.compile("^[a-zA-Z0-9_/-]{6,16}$");
		if (!p.matcher(account).matches()) {
			throw new RequestParameterInvalidException("username:用户名允许6-16个字符,包括英文字母,数字,下划线,减号");
		}

	}

	/**
	 * 验证密码
	 * 
	 * @throws RequestParameterInvalidException
	 */
	public static void validatePassword(String password) throws RequestParameterInvalidException {
		if (StringKit.isEmpty(password)) {
			throw new RequestParameterInvalidException("password:密码不能为空");
		}
		Pattern p = Pattern.compile("^.{6,16}$");
		if (!p.matcher(password).matches()) {
			throw new RequestParameterInvalidException("password:密码允许6-16个字符,区分大小写");
		}
	}

	/**
	 * 验证新密码
	 * 
	 * @throws RequestParameterInvalidException
	 */
	public static void validateNewPassword(String password) throws RequestParameterInvalidException {
		if (StringKit.isEmpty(password)) {
			throw new RequestParameterInvalidException("newPassword:密码不能为空");
		}
		Pattern p = Pattern.compile("^.{6,16}$");
		if (!p.matcher(password).matches()) {
			throw new RequestParameterInvalidException("newPassword:密码允许6-16个字符,区分大小写");
		}
	}

	/**
	 * 验证姓名
	 * 
	 * @throws RequestParameterInvalidException
	 */
	public static void validateRealName(String realName) throws RequestParameterInvalidException {
		if (StringKit.isEmpty(realName)) {
			throw new RequestParameterInvalidException("realName:昵称不能为空");
		}
		// Pattern p = Pattern.compile("^[a-zA-Z0-9_/-\u4e00-\u9fa5]+{0,11}$");
		Pattern p = Pattern.compile("^.{1,12}$");
		if (!p.matcher(realName).matches()) {
			throw new RequestParameterInvalidException("realName:昵称允许1-12个字符,区分大小写");
		}
	}

	/**
	 * 验证性别
	 * 
	 * @param gender
	 * @throws RequestParameterInvalidException
	 */
	public static void validateGender(int gender) throws RequestParameterInvalidException {
		Gender g = Gender.convertValue(gender);
		if (g == null) {
			throw new RequestParameterInvalidException("gender:性别必填");
		}
	}

	/**
	 * 验证url
	 * 
	 * @throws RequestParameterInvalidException
	 */
	public static void validateUrl(String url) throws RequestParameterInvalidException {
		if (url == null) {
			return;
		}
		if (url.length() > 100) {
			throw new RequestParameterInvalidException("url:长度大于100");
		}
		//
		// // 正则表达式检验ip地址
		// String ipRegex = "((25[0-5]|2[0-4]\\d|1\\d\\d|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d\\d|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d\\d|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d\\d|\\d{1,2}))";
		// // 正则表达式检验域名
		// String domain = "([0-9a-z][0-9a-z-]*\\.)*[a-z]{2,6}|localhost";
		// // 检验端口
		// String port = "(:[0-9]{1,4})";
		// // 检验剩余的URL是否合法其中[\u4e00-\u9fa5]表示中文
		// String remain = "(/[\\w!~*'()\\.;?:@&=+$,%#-[\u4e00-\u9fa5]]*)+/?$";
		// // 该表达式用于检验http格式的URL是否合法
		// Pattern httpRegex = Pattern.compile("^http://" + "(" + ipRegex + "|" + domain + ")" + port + "?" + remain);
		// if (!httpRegex.matcher(url).matches()) {
		// throw new RequestParameterInvalidException("url:这不是一个合法的url地址");
		// }
	}

	public static void validateBook(String interest) throws RequestParameterInvalidException {
		if (interest.length() > 1000) {
			throw new RequestParameterInvalidException("book:长度大于1000了");
		}
	}

	public static void validateFilm(String interest) throws RequestParameterInvalidException {
		if (interest.length() > 1000) {
			throw new RequestParameterInvalidException("film:长度大于1000了");
		}
	}

	public static void validateMusic(String interest) throws RequestParameterInvalidException {
		if (interest.length() > 1000) {
			throw new RequestParameterInvalidException("music:长度大于1000了");
		}
	}

	public static void validateIdol(String interest) throws RequestParameterInvalidException {
		if (interest.length() > 1000) {
			throw new RequestParameterInvalidException("idol:长度大于1000了");
		}
	}

	public static void validateHope(String interest) throws RequestParameterInvalidException {
		if (interest.length() > 1000) {
			throw new RequestParameterInvalidException("hope:长度大于1000了");
		}
	}

	/**
	 * @param cookiePeopleId
	 * @throws RequestParameterInvalidException
	 */
	public static void validateId(long cookiePeopleId) throws RequestParameterInvalidException {
		if (IdCreator.isNotValidId(cookiePeopleId)) {
			throw new RequestParameterInvalidException("id:id无效");
		}
	}

	/**
	 * @param intro
	 * @throws RequestParameterInvalidException
	 */
	public static void validateIntro(String intro) throws RequestParameterInvalidException {
		if (intro == null) {
			return;
		}
		if (intro.length() > 1000) {
			throw new RequestParameterInvalidException("intro:长度大于1000");
		}
	}

	/**
	 * @param year
	 * @param month
	 * @param day
	 * @throws RequestParameterInvalidException
	 */
	public static void validateBirthday(int year, int month, int day) throws RequestParameterInvalidException {
		if (year < 1) {
			throw new RequestParameterInvalidException("year:invalid");
		}
		if (month < 1 || month > 12) {
			throw new RequestParameterInvalidException("month:invalid");
		}
		if (day < 1 || day > 32) {
			throw new RequestParameterInvalidException("day:invalid");
		}
	}

	/**
	 * @param provinceId
	 * @param cityId
	 * @param countyId
	 */
	public static void validateArea(int provinceId, int cityId, int countyId) {
		// FIXME
	}

	/**
	 * @param hometownProvinceId
	 * @param hometownCityId
	 * @param hometownCountyId
	 */
	public static void validateHometown(int hometownProvinceId, int hometownCityId, int hometownCountyId) {
		// FIXME
	}
}
