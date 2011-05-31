package q.web.group;

import java.util.regex.Pattern;

import q.util.StringKit;
import q.web.exception.RequestParameterInvalidException;

public class GroupValidator {
	/**
	 * 验证姓名
	 *
	 * @throws RequestParameterInvalidException
	 */
	public static void validateGroupName(String realName) throws RequestParameterInvalidException {
		if (StringKit.isEmpty(realName)) {
			throw new RequestParameterInvalidException("groupName:圈子名称不能为空");
		}
		// Pattern p = Pattern.compile("^[a-zA-Z0-9_/-\u4e00-\u9fa5]+{0,11}$");
		Pattern p = Pattern.compile("^.{1,20}$");
		if (!p.matcher(realName).matches()) {
			throw new RequestParameterInvalidException("groupName:圈子名称允许1-20个字符,区分大小写");
		}
	}

}
