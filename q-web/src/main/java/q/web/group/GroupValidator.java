package q.web.group;

import java.sql.SQLException;
import java.util.regex.Pattern;

import q.dao.CategoryDao;
import q.domain.Category;
import q.util.IdCreator;
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
//		p = Pattern.compile("\\");
//		if(p.matcher(realName).matches()) {
//			throw new RequestParameterInvalidException("groupName:非法字符");
//		}

	}

	public static void validateCategory(long categoryId) throws RequestParameterInvalidException, SQLException {
		if (IdCreator.isNotValidId(categoryId)) {
			throw new RequestParameterInvalidException("category:invalid");
		}
	}

	public static void validateImg(String url) throws RequestParameterInvalidException, SQLException {
      if(!StringKit.isEmpty(url)){
		if(!url.substring(0,4).equals("http")){
	    	throw new RequestParameterInvalidException("imgUrl:invalid");
		}
      }
	}

	public static void validateIntro(String intro) throws RequestParameterInvalidException, SQLException {
		if (StringKit.isEmpty(intro)) {
			throw new RequestParameterInvalidException("intro:圈子简介不能为空");
		}
		if(intro.length() > 140) {
			throw new RequestParameterInvalidException("intro:圈子简介超过140字了");
		}

	}


	public static void validateGroupId(long groupId) throws RequestParameterInvalidException, SQLException {
		if (IdCreator.isNotValidId(groupId)) {
			throw new RequestParameterInvalidException("groupId:invalid");
		}
	}


}
