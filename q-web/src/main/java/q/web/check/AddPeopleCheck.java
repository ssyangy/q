package q.web.check;

import q.biz.exception.RequestParameterInvalidException;
import q.biz.exception.PeopleAlreadyExistException;
import q.dao.PeopleDao;
import q.domain.People;
import q.log.Logger;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import sun.rmi.runtime.Log;

public class AddPeopleCheck extends Resource {
	private PeopleDao peopleDao;
	private final static Logger log = Logger.getLogger();

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		String email = context.getString("email");
		People result = this.peopleDao.getPeopleByEmail(email);
		if (result != null) {
			throw new PeopleAlreadyExistException("该邮箱地址已经被使用");
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {

		String email = context.getString("email");
		log.error(email);
		if (StringKit.isEmpty(email)) {
			throw new RequestParameterInvalidException("email参数丢失");
		}
	}

}
