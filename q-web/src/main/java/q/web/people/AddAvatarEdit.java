package q.web.people;

import q.dao.PeopleDao;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleAlreadyExistException;
import q.web.exception.RequestParameterInvalidException;

public class AddAvatarEdit extends Resource{

	@Override
	public void execute(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		double x1=Double.parseDouble(context.getString("realx1"));
		double x2=Double.parseDouble(context.getString("realx2"));
		double y1=Double.parseDouble(context.getString("realy1"));
		double y2=Double.parseDouble(context.getString("realy2"));
		//处理这图片吧。上面是左上角和右下角的参数。
		throw new RequestParameterInvalidException("value:图片参数出错了。");
	}

}
